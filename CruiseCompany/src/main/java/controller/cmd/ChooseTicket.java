package controller.cmd;

import controller.util.*;
import model.dao.FactoryDAO;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.ExcursionService;
import model.service.TicketService;
import model.util.TicketClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChooseTicket implements Action {
    private TicketService ticketService;
    private ExcursionService excursionService;

    ChooseTicket() {
        ticketService = new TicketService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
        excursionService = new ExcursionService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String ticketType = RequestParser.getTicketType(request.getRequestURI());
        Tour tour = (Tour) session.getAttribute("tour");
        if (ticketType == null || ticketType.isEmpty() ||  tour == null)
            return ActionResponse.Default();

        Ticket ticket = ticketService.chooseTicket(TicketClass
                .valueOf(ticketType.toUpperCase()), tour.getId());
        if (ticket == null)
            return ActionResponse.Default();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.setTicket(ticket);
        session.setAttribute("excursions",
                excursionService.showCruiseExcursion(tour.getId()));
        return new ActionResponse(Act.FORWARD, URI.TICKET_JSP);
    }
}
