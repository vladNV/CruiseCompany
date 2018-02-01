package model.dao.interfaces;

import model.entity.User;
import model.exceptions.ServiceException;

/**
 * UserDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Gets user by email
     * @param email - users email
     * @return user
     */
    User findByEmail(String email);

    /**
     * Withdraws money from the bill
     * @param card - card number
     * @param CVV - CVV number
     * @param money - amount of money
     * @throws ServiceException - User does not have enough money
     */
    void takeMoney(long card, int CVV, long money) throws ServiceException;

    /**
     * Puts money on the bill
     * @param card - card number
     * @param money - amount of money
     */
    void putMoney(long card, long money);

    /**
     * Checks existing user in the bank
     * @param card - card number
     * @param CVV - CVV number
     * @throws ServiceException - user doesn't exist
     */
    void existUser(long card, int CVV) throws ServiceException;

}
