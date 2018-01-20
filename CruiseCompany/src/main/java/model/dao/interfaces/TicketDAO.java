package model.dao.interfaces;

import model.entity.Ticket;
import model.util.AggregateOperation;
import model.util.TicketClass;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> ticketsForCruise(int tourId);
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);
    Ticket findTicketByType(TicketClass type);
    void updateTicket(Ticket ticket, int userId);

}
