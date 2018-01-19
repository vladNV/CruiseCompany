package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.RouteDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.interfaces.TourDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.Ticket;
import model.entity.Tour;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourService {

    private FactoryDAO factory;

    public TourService(FactoryDAO factory) {
        this.factory = factory;
    }

    public List<Tour> showTours() {
        //TODO mysql connect change to connection pool
        try {
            return factory.tourDAO(FactoryMySQL.connect()).joinWithShip();
            // service exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Tour allInformationAboutTour(int tourId)  {
        Connection connect = FactoryMySQL.connect();
        try (TourDAO tourDAO = factory.tourDAO(connect);
             TicketDAO ticketDAO = factory.ticketDAO(connect);
             RouteDAO routeDAO = factory.routeDAO(connect)) {
            connect.setAutoCommit(false);
            Tour tour = tourDAO.findTourWithShip(tourId);
            //optional ?
            if (tour == null) return null;
            tour.setRoutes(routeDAO.routesOfCruise(tourId));
            tour.setTickets(ticketDAO.ticketsForCruise(tourId));
            connect.commit();
            return tour;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
