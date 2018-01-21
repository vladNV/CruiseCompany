package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.interfaces.TicketDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.util.AggregateOperation;
import model.util.TicketClass;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class TicketService {
    private FactoryDAO factory;

    public TicketService(FactoryDAO factory) {
        this.factory = factory;
    }

    public List<AggregateOperation<Integer, Ticket>> amountTicket(int tourId) {
        try (TicketDAO ticketDAO = factory.ticketDAO(FactoryMySQL.connect())) {
            return ticketDAO.ticketForCategory(tourId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket chooseTicket(TicketClass type, int tourId) {
        try (TicketDAO ticketDAO = factory.ticketDAO(FactoryMySQL.connect())) {
            return ticketDAO.findTicketByType(type, tourId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean buyTicket(Ticket ticket, Set<Excursion> excursions,
                             User user) {
        Connection connect = FactoryMySQL.connect();
        try (TicketDAO ticketDAO = factory.ticketDAO(connect);
             ExcursionDAO excursionDAO = factory.excursionDAO(connect)){
            connect.setAutoCommit(false);
            ticketDAO.updateTicket(ticket, user.getId());
            excursionDAO.updateUserExcursion(ticket, excursions);
            connect.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
