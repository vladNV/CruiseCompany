package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.Tour;
import model.entity.User;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;

import java.util.List;

/**
 * TicketDAO.java - an interface of parameterize dao.
 * Defines methods for saving and retrieving data from database.
 *
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public interface TicketDAO extends GenericDAO<Ticket> {

    /**
     * Gets all users tickets
     * @param userId - user identifier
     * @return tickets list
     */
    List<Ticket> userTickets(int userId);

    /**
     *  Gets all cruises tickets
     * @param tourId - tour identifier
     * @return tickets list
     */
    List<Ticket> tourTickets(int tourId);

    /**
     * Gets all categories tickets
     * @param tourId - tour identifier
     * @return tickets list
     */
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);

    /**
     * Gets ticket by Id
     * @param ticketId - ticket identifier
     * @return ticket
     */
    Ticket getTourTicket(int ticketId);

    /**
     * Updates ticket by Id
     * @param ticket - new ticket info
     * @param userId - user identifier
     * @throws ServiceException - ticket is already bought
     */
    void updateTicket(Ticket ticket, int userId) throws ServiceException;

    /**
     * Checks tickets for existing
     * @param userId - user identifier
     * @param tourId - tour identifier
     * @throws ServiceException - user already bought this ticket
     */
    void existTicket(int userId, int tourId) throws ServiceException;

    /**
     * Generates tickets for tour
     * @param tour - updated tour
     */
    void setTicketsOnTour(Tour tour);

}
