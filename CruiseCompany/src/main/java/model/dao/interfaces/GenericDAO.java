package model.dao.interfaces;

import java.util.List;
/**
 * GenericDAO.java - an interface of parametrized dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @param <E> entity
 * @author  Nagaev Vladislav
 * @version 1.0
 */
interface GenericDAO<E> extends AutoCloseable {

    /**
     * Creates generic entity.
     * Returns primary key, if entity's data inserted to the database.
     * Returns 0, if entity's data did not insert to the database.
     *
     * @param e a entity object ready to insert
     * @return auto generated primary key
     */
    int insert(E e);

    /**
     * Updates existing generic entity.
     *
     * @param e an modified existing entity
     */
    void update(E e);

    /**
     * Deletes existing generic entity of specified id.
     *
     * @param id an id of desired entity
     */
    void delete(int id);

    /**
     * Returns the generic entity of specified id.
     *
     * @param id an id of desired entity
     * @return the generic object of the specified id,
     * or null if such does not exist or {@link java.sql.SQLException SQLException} was thrown
     */
    E findById(int id);

    /**
     * Returns a list of existing entities
     *
     * @return the List object parametrized by generic entity,
     * or null if {@link java.sql.SQLException SQLException} was thrown
     */
    List<E> findAll();

}
