package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User extractIf(ResultSet set) throws SQLException {
        if (set.next()) {
            extractUser(set);
        }
        return null;
    }

    public static List<User> extractWhile(ResultSet set)
            throws SQLException {
        List<User> users = new ArrayList<>();
        while (set.next()) {
            users.add(extractUser(set));
        }
        return users;
    }

    private static User extractUser(ResultSet set) throws SQLException {
        return User.newUser()
                .id(set.getInt("iduser"))
                .email(set.getString("email"))
                .login(set.getString("login"))
                .password(set.getString("password"))
                .build();
    }
}
