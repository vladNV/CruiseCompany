package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.UserDAO;
import model.dao.ConnectionPool;
import model.entity.User;
import model.exceptions.UniqueException;
import model.util.MD5;
import org.apache.log4j.Logger;

/**
 * User service
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public class UserService {
    private final static Logger logger = Logger.getLogger(UserService.class);
    private FactoryDAO factory;

    public UserService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    /**
     * Registers new user.
     * @param pass - user password.
     * @param login - user login.
     * @param email - user email, should be unique.
     * @return if user created returns true, otherwise false.
     * @throws UniqueException when such email already exists.
     */
    public boolean registration(String pass, String login, String email)
            throws UniqueException{
        logger.info("registration " + login + "," + email);
        pass = MD5.getHashCode(pass);
        User user = User.newUser()
                .password(pass)
                .login(login)
                .email(email)
                .build();
        try (UserDAO userDAO = factory.userDAO(ConnectionPool.pool().connect())) {
            int id;
            id = userDAO.insert(user);
            user.setId(id);
            logger.info("success registration for " + user.getEmail());
            return true;
        } catch (UniqueException e) {
            throw new UniqueException(e);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sign in method.
     * Returns null when such user does not exist in database.
     * @param email - user email.
     * @param password - user password.
     * @return authorized user, or null.
     */
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

    @Deprecated
    public boolean uniqueEmail(String email) {
        User existUser = factory.userDAO(ConnectionPool.pool().connect()).findByEmail(email);
        return existUser == null;
    }

    /**
     * Compares passwords, entered password by client and password from database.
     * @param inPass entered password.
     * @param user - user from database.
     * @return if passwords are equal returns true, otherwise false.
     */
    private boolean passwordEquals(String inPass, User user) {
        return user.getPassword().equals(inPass);
    }

}
