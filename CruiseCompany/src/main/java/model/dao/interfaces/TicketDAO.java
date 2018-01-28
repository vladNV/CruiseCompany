package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.Tour;
import model.entity.User;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> userTickets(int userId);
    List<Ticket> tourTickets(int tourId);
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);
    Ticket getTourTicket(int ticketId);
    void updateTicket(Ticket ticket, int userId) throws ServiceException;
    void existTicket(int userId, int tourId) throws ServiceException;
    void setTicketsOnTour(Tour tour);

}
