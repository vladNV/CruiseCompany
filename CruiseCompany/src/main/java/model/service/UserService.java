package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.UserDAO;
import model.dao.mysql.ConnectionPool;
import model.dao.mysql.FactoryMySQL;
import model.entity.User;
import model.util.MD5;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final static Logger logger = Logger.getLogger(UserService.class);
    private FactoryDAO factory;

    public UserService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public boolean registration(String pass, String login, String email) {
        logger.info("registration " + login + "," + email);
        pass = MD5.getHashCode(pass);
        User user = User.newUser()
                .password(pass)
                .login(login)
                .email(email)
                .build();
        try (UserDAO userDAO = factory.userDAO(ConnectionPool.pool().connect())) {
            int id = userDAO.insert(user);
            if (id < 0) return false;
            user.setId(id);
            logger.info("success registration for " + user.getEmail());
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public User signIn(String email, String password) {
        password = MD5.getHashCode(password);
        try (UserDAO userDAO = factory.userDAO(ConnectionPool.pool().connect())){
            User user = userDAO.findByEmail(email);
            if (user == null) return null;
            return passwordEquals(password, user) ? user : null;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public boolean uniqueEmail(String email) {
        User existUser = factory.userDAO(ConnectionPool.pool().connect()).findByEmail(email);
        return existUser == null;
    }

    private boolean passwordEquals(String inPass, User user) {
        return user.getPassword().equals(inPass);
    }

}
