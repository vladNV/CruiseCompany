package model.dao.interfaces;

import model.entity.Ticket;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<Ticket> ticketsForCruise(int tourId);

}
