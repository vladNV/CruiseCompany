package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.RouteDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.interfaces.TourDAO;
import model.dao.mysql.ConnectionPool;
import model.dao.mysql.FactoryMySQL;
import model.entity.Ticket;
import model.entity.Tour;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourService {

    private FactoryDAO factory;

    public TourService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<Tour> showTours() {
        try (TourDAO tourDAO = factory.tourDAO(ConnectionPool.pool().connect())) {
            return tourDAO.joinWithShip();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Tour allInformationAboutTour(int tourId)  {
        Connection connect = ConnectionPool.pool().connect();
        try (TourDAO tourDAO = factory.tourDAO(connect);
             RouteDAO routeDAO = factory.routeDAO(connect)) {
            connect.setAutoCommit(false);
            Tour tour = tourDAO.findTourWithShip(tourId);
            //optional ?
            if (tour == null) return null;
            tour.setRoutes(routeDAO.routesOfCruise(tourId));
            connect.commit();
            return tour;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
