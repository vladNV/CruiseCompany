package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.Ticket;
import model.util.AggregateOperation;

import java.util.List;

public class TicketService {
    private FactoryDAO factory;

    public TicketService(FactoryDAO factory) {
        this.factory = factory;
    }

    public List<AggregateOperation<Integer, Ticket>> amountTicket(int tourId) {
        TicketDAO ticketDAO = factory.ticketDAO(FactoryMySQL.connect());
        return ticketDAO.ticketForCategory(tourId);
    }

}
