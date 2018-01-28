package model.dao.interfaces;

import model.entity.User;
import model.exceptions.ServiceException;

public interface UserDAO extends GenericDAO<User> {

    User findByEmail(String email);
    void takeMoney(long card, int CVV, long money) throws ServiceException;
    void putMoney(long card, long money);
    void existUser(long card, int CVV) throws ServiceException;

}
