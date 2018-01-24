package model.dao.interfaces;

import model.entity.Route;

import java.util.List;

public interface RouteDAO extends GenericDAO<Route> {

    List<Route> routesOfCruise(int tourId);
    void setRoutes(List<Route> routes, int tourId);

}
