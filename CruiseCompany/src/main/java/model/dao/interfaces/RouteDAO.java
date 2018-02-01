package model.dao.interfaces;

import model.entity.Route;

import java.util.List;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface RouteDAO extends GenericDAO<Route> {

    /**
     * Returns list with routes for cruise number.
     * @param tourId unique cruise number.
     * @return list with routes.
     */
    List<Route> routesOfCruise(int tourId);

    /**
     * Inserts cruise routes to database.
     * @param routes list with routes
     * @param tourId unique cruise number
     */
    void setRoutes(List<Route> routes, int tourId);

}
