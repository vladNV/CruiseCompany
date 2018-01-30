package model.service;

import controller.util.Currency;
import model.CruiseCompany;
import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.interfaces.RouteDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.interfaces.UserDAO;
import model.dao.ConnectionPool;
import model.entity.*;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.*;

public class TicketService {
    private final static Logger logger = Logger.getLogger(TicketService.class);

    private FactoryDAO factory;

    public TicketService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<AggregateOperation<Integer, Ticket>> amountTicket(int tourId) {
        logger.info("amount ticket: " + tourId);
        try (TicketDAO ticketDAO = factory.ticketDAO(ConnectionPool.pool().connect())) {
            return ticketDAO.ticketForCategory(tourId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> showTicketsForTour(int tourId) {
        logger.info("show tickets for tour " + tourId);
        try (TicketDAO ticketDAO = factory.ticketDAO(ConnectionPool.pool().connect())){
            return ticketDAO.tourTickets(tourId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public Ticket chooseTicket(int ticketId) {
        logger.info("choose ticket: " + ticketId);
        Connection connection = ConnectionPool.pool().connect();
        try (TicketDAO ticketDAO = factory.ticketDAO(connection);
            RouteDAO routeDAO = factory.routeDAO(connection);
            ExcursionDAO excursionDAO = factory.excursionDAO(connection)) {
            connection.setAutoCommit(false);
            Ticket ticket = ticketDAO.getTourTicket(ticketId);
            Tour tour = ticket.getTour();
            List<Route> routes = routeDAO.routesOfCruise(tour.getId());
            List<Excursion> excursions = excursionDAO.cruiseExcursion(tour.getId());
            tour.setRoutes(putExcursionInTour(routes, excursions));
            connection.commit();
            return ticket;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    private List<Route> putExcursionInTour(final List<Route> routes,
                                           final List<Excursion> excursions) {
        final HashMap<Port, List<Excursion>> map = new HashMap<>();
        Port port;
        for (Excursion ex : excursions) {
            port = ex.getPort();
            map.computeIfAbsent(port, v -> new ArrayList<>()).add(ex);
        }
        for (Route r : routes) {
            port = r.getPort();
            port.setExcursions(map.get(port));
        }
        return routes;
    }

    public void buyTicket(Ticket ticket, Set<Excursion> excursions,
                             User user, long money, long card, int cvv)
            throws ServiceException {
        logger.info("buy ticket");
        Connection connect = ConnectionPool.pool().connect();
        try (TicketDAO ticketDAO = factory.ticketDAO(connect);
             ExcursionDAO excursionDAO = factory.excursionDAO(connect);
             UserDAO userDAO = factory.userDAO(connect)){
            logger.info("start transaction for " + ticket.getId() + " user, " + user.getId());
            connect.setAutoCommit(false);
            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            userDAO.existUser(card, cvv);
            ticketDAO.existTicket(user.getId(), ticket.getTour().getId());
            userDAO.takeMoney(card, cvv, money);
            userDAO.putMoney(CruiseCompany.CARD, money);
            ticketDAO.updateTicket(ticket, user.getId());
            excursionDAO.updateUserExcursion(ticket, excursions);
            connect.commit();
            logger.info("success transaction");
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public Tuple<List<Ticket>, List<Ticket>>
    userTickets(User user, LocalDateTime to) {
        logger.info("user tickets: " + user.getId());
        try (TicketDAO ticketDAO = factory
                .ticketDAO(ConnectionPool.pool().connect())) {
            List<Ticket> all = ticketDAO.userTickets(user.getId());
            return delimListForDate(to, all);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    private Tuple<List<Ticket>, List<Ticket>> delimListForDate(LocalDateTime to,
                                                               List<Ticket> all) {
        logger.info("delim list for date");
        List<Ticket> active = new ArrayList<>();
        List<Ticket> old = new ArrayList<>();
        for (Ticket t : all) {
           if (t.getTour().getArrival().compareTo(to) > 0) {
               active.add(t);
           } else {
               old.add(t);
           }
        }
        return new Tuple<>(active, old);
    }

    public List<Ticket> extractTicket(String[] prices, HashMap<TicketClass,
                                      List<TicketBonus>> map, int[] quantity) {
        TicketClass[] types = TicketClass.values();
        List<Ticket> tickets = new ArrayList<>();
        for (TicketClass type : types) {
            for (int i = 0; i < quantity[type.ordinal()]; i++) {
                tickets.add(Ticket.newTicket()
                        .bonus(map.get(type))
                        .price(Long.parseLong(
                                prices[type.ordinal()]) * Currency.Const.ACCURACY
                        ).type(type)
                        .build());
            }
        }
        return tickets;
    }
}
