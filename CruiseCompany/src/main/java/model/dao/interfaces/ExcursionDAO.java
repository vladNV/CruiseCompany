package model.dao.interfaces;

import model.entity.Excursion;
import model.entity.Ticket;

import java.util.List;
import java.util.Set;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface ExcursionDAO extends GenericDAO<Excursion> {

    /**
     * Returns list of excursions combined with ports
     * @return list with excursions
     */
    List<Excursion> joinWithPort();

    /**
     * Returns list with excursions for concrete cruise
     * @param tourId unique number cruise
     * @return list wit excursions
     */
    List<Excursion> cruiseExcursion(int tourId);

    /**
     * Adds excursions for user to database
     * @param ticket purchased by user
     * @param excursions which selected by user
     */
    void updateUserExcursion(Ticket ticket, Set<Excursion> excursions);

}
