package model.dao.interfaces;

import model.entity.Tour;

import java.util.List;

/**
 * TourDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface TourDAO extends GenericDAO<Tour> {

    /**
     * Presents union of tour and ship tabels
     * @param offset - offset on table
     * @return list of tours
     */
    List<Tour> joinWithShip(int offset);

    /**
     * Search tours by region
     * @param region - region
     * @param offset - offset on table
     * @return list of tours
     */
    List<Tour> search(String region, int offset);

    /**
     * Gets tour with ship by Id
     * @param tourId - tour identifier
     * @return tour
     */
    Tour findTourWithShip(int tourId);

    /**
     * Gets number of tours
     * @return number
     */
    int amount();

    /**
     * Gets number of tours by name
     * @param name - tour name
     * @return number
     */
    int amount(String name);

}
