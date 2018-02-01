package model.dao.interfaces;

import model.entity.Tour;

import java.util.List;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface TourDAO extends GenericDAO<Tour> {

    /**
     *
     * @param offset
     * @return
     */
    List<Tour> joinWithShip(int offset);

    /**
     *
     * @param region
     * @param offset
     * @return
     */
    List<Tour> search(String region, int offset);

    /**
     *
     * @param tourId
     * @return
     */
    Tour findTourWithShip(int tourId);

    /**
     *
     * @return
     */
    int amount();

    /**
     *
     * @param name
     * @return
     */
    int amount(String name);

}
