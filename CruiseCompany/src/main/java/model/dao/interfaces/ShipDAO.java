package model.dao.interfaces;

import model.entity.Ship;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface ShipDAO extends GenericDAO<Ship> {

    /**
     * Returns list with ship, who are free.
     * Returns empty list when all ships already in tour
     * @param departure ship departure time
     * @param arrival ship arrival time
     * @return list with ships
     */
    List<Ship> findFreeShip(LocalDateTime departure,
                            LocalDateTime arrival);


}
