package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.util.AggregateOperation;
import model.util.TicketClass;
import model.util.Tuple;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> userTickets(int userId);
    List<Ticket> ticketsForCruise(int tourId);
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);
    Ticket findTicketByType(TicketClass type, int tourId);
    void updateTicket(Ticket ticket, int userId) throws ServiceException;
    void existTicket(int userId, int tourId) throws ServiceException;

}
