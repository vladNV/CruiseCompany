package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.Tour;
import model.entity.User;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;

import java.util.List;

/**
 * ExcursionDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface TicketDAO extends GenericDAO<Ticket> {

    /**
     *
     * @param userId
     * @return
     */
    List<Ticket> userTickets(int userId);

    /**
     *
     * @param tourId
     * @return
     */
    List<Ticket> tourTickets(int tourId);

    /**
     *
     * @param tourId
     * @return
     */
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);

    /**
     *
     * @param ticketId
     * @return
     */
    Ticket getTourTicket(int ticketId);

    /**
     *
     * @param ticket
     * @param userId
     * @throws ServiceException
     */
    void updateTicket(Ticket ticket, int userId) throws ServiceException;

    /**
     *
     * @param userId
     * @param tourId
     * @throws ServiceException
     */
    void existTicket(int userId, int tourId) throws ServiceException;

    /**
     *
     * @param tour
     */
    void setTicketsOnTour(Tour tour);

}
