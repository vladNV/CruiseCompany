package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.RouteDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.interfaces.TourDAO;
import model.dao.ConnectionPool;
import model.entity.Tour;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * Tour service
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public class TourService {
    private final static Logger logger = Logger.getLogger(TourService.class);

    private FactoryDAO factory;
    public static final int LIMIT_TOUR = 5;

    public TourService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<Tour> showTours(int page) {
        logger.info("show tours");
        try (TourDAO tourDAO = factory.tourDAO(ConnectionPool.pool().connect())) {
            return tourDAO.joinWithShip(offset(page));
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all information about the tour
     * @param tourId- tour identifier
     * @return tour
     */
    public Tour allInformationAboutTour(int tourId)  {
        logger.info("all information about tour");
        Connection connect = ConnectionPool.pool().connect();
        try (TourDAO tourDAO = factory.tourDAO(connect);
             RouteDAO routeDAO = factory.routeDAO(connect)) {
            logger.info("start transaction for " + tourId);
            connect.setAutoCommit(false);
            Tour tour = tourDAO.findTourWithShip(tourId);
            if (tour == null) return null;
            tour.setRoutes(routeDAO.routesOfCruise(tourId));
            connect.commit();
            logger.info("success transaction");
            return tour;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Gets all tours in the region
     * @param region - region name
     * @param page - page number
     * @return tours list
     */
    public List<Tour> searchTourForRegion(String region, int page) {
        logger.info("search tour for region: " + region);
        try (TourDAO tourDAO = factory.tourDAO(ConnectionPool.pool().connect())) {
            return tourDAO.search(region, offset(page));
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates new tour
     * @param tour - information about tour
     */
    public void createNewTour(Tour tour) {
        logger.info("create new tour");
        Connection connect = ConnectionPool.pool().connect();
        try (TourDAO tourDAO = factory.tourDAO(connect);
             TicketDAO ticketDAO = factory.ticketDAO(connect);
             RouteDAO routeDAO = factory.routeDAO(connect)){
            logger.info("start transaction for " + tour.getId() + ","
                    + tour.getName());
            connect.setAutoCommit(false);
            tour.setId(tourDAO.insert(tour));
            routeDAO.setRoutes(tour.getRoutes(), tour.getId());
            ticketDAO.setTicketsOnTour(tour);
            connect.commit();
            logger.info("success transaction");
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Counts numbers of pages
     * @param search - search string
     * @return number
     */
    public int quantityOfPages(String search) {
        try (TourDAO tourDAO = factory.tourDAO(ConnectionPool.pool().connect())){
            if (search == null) {
                return tourDAO.amount();
            } else {
                return tourDAO.amount(search);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    private int offset(int pageNumber) {
        return (pageNumber * LIMIT_TOUR) - LIMIT_TOUR;
    }


}
