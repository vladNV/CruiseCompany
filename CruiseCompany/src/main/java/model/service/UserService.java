package model.service;

import model.dao.FactoryDAO;
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
        Connection connect = FactoryMySQL.connect();
        User user = User.newUser()
                .password(pass)
                .login(login)
                .email(email)
                .build();
        int id = factory.userDAO(connect).insert(user);
        if (id < 0) return false;
        user.setId(id);
        return true;
    }

    public boolean uniqueEmail(String email) {
        User existUser = factory.userDAO(FactoryMySQL.connect()).findByEmail(email);
        return existUser == null;
    }

    public User signIn(String email, String password) throws SQLException {
        password = MD5.getHashCode(password);
        User user = factory.userDAO(FactoryMySQL.connect()).findByEmail(email);
        if (user == null) return null;
        return passwordEquals(password, user) ? user : null;
    }

    private boolean passwordEquals(String inPass, User user) {
        return user.getPassword().equals(inPass);
    }

}
