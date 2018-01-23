package model.dao.mapper;

import model.entity.Role;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User extract(ResultSet rs) throws SQLException {
        return User.newUser()
                .id(rs.getInt("iduser"))
                .email(rs.getString("email"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role").toUpperCase()))
                .build();
    }
}
