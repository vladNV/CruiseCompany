package model.dao.interfaces;

import model.entity.User;
import model.exceptions.ServiceException;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     *
     * @param card
     * @param CVV
     * @param money
     * @throws ServiceException
     */
    void takeMoney(long card, int CVV, long money) throws ServiceException;

    /**
     *
     * @param card
     * @param money
     */
    void putMoney(long card, long money);

    /**
     *
     * @param card
     * @param CVV
     * @throws ServiceException
     */
    void existUser(long card, int CVV) throws ServiceException;

}
