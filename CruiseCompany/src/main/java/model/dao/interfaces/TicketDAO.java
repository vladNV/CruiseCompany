package model.dao.interfaces;

import model.entity.Ticket;
import model.util.AggregateOperation;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> ticketsForCruise(int tourId);
    List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId);

}
