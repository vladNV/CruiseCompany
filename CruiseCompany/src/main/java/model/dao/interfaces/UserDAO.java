package model.dao.interfaces;

import model.entity.User;

public interface UserDAO extends GenericDAO<User> {

    User findByEmail(String email);

}
