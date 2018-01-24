package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.Tour;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> ticketsForId(int id);
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);
    Ticket findTicketByType(TicketClass type, int tourId);
    void updateTicket(Ticket ticket, int userId) throws ServiceException;
    void existTicket(int userId, int tourId) throws ServiceException;
    void setTicketsOnTour(Tour tour);

}
