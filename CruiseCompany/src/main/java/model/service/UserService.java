package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.UserDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.User;
import model.util.MD5;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private FactoryDAO factory;

    public UserService(FactoryDAO factory) {
        this.factory = factory;
    }

    public boolean registration(String pass, String login, String email) {
        pass = MD5.getHashCode(pass);
        User user = User.newUser()
                .password(pass)
                .login(login)
                .email(email)
                .build();
        try (UserDAO userDAO = factory.userDAO(FactoryMySQL.connect())) {
            int id = userDAO.insert(user);
            if (id < 0) return false;
            user.setId(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User signIn(String email, String password) {
        password = MD5.getHashCode(password);
        try (UserDAO userDAO = factory.userDAO(FactoryMySQL.connect())){
            User user = userDAO.findByEmail(email);
            if (user == null) return null;
            return passwordEquals(password, user) ? user : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean uniqueEmail(String email) {
        User existUser = factory.userDAO(FactoryMySQL.connect()).findByEmail(email);
        return existUser == null;
    }

    private boolean passwordEquals(String inPass, User user) {
        return user.getPassword().equals(inPass);
    }

}
