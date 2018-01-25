package model.service;

import model.CruiseCompany;
import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.interfaces.UserDAO;
import model.dao.mysql.ConnectionPool;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;
import model.entity.Tuple;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Ticket chooseTicket(TicketClass type, int tourId) {
        logger.info("choose ticket: " + tourId + "," + type);
        try (TicketDAO ticketDAO = factory.ticketDAO(ConnectionPool.pool().connect())) {
            return ticketDAO.findTicketByType(type, tourId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
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
    userTickets(int userId, LocalDateTime to) {
        logger.info("user tickets: " + userId);
        try (TicketDAO ticketDAO = factory
                .ticketDAO(ConnectionPool.pool().connect())) {
            List<Ticket> all = ticketDAO.ticketsForId(userId);
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
           if (t.getArrival().compareTo(to) > 0) {
               active.add(t);
           } else {
               old.add(t);
           }
        }
        return new Tuple<>(active, old);
    }

    public List<Ticket> extractTicket(String[] prices, String[] types,
                                      String[] amounts, LocalDateTime departure,
                                      LocalDateTime arrival) {
        List<Ticket> tickets = new ArrayList<>();
        int amount;
        long price;
        TicketClass type;
        for (int i = 0; i < amounts.length; i++) {
            amount = Integer.parseInt(amounts[i]);
            type = TicketClass.valueOf(types[i].toUpperCase());
            price = Long.parseLong(prices[i]);
            for (int j = 0; j < amount; j++) {
                tickets.add(Ticket.newTicket()
                        .price(price)
                        .type(type)
                        .departure(departure)
                        .arrival(arrival)
                        .build());
            }
        }
        return tickets;
    }
}
