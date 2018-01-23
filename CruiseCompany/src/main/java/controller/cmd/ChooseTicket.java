package controller.cmd;

import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Ticket;
import model.service.ExcursionService;
import model.service.TicketService;
import model.entity.TicketClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;

public class ChooseTicket implements Action {
    private TicketService ticketService;
    private ExcursionService excursionService;

    ChooseTicket() {
        ticketService = new TicketService();
        excursionService = new ExcursionService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                   HttpServletResponse response) {
        HttpSession session = request.getSession();
        String ticketType = RequestParser.getTicketType(request.getRequestURI());
        int tour = RequestParser.getIdFromURI(request.getRequestURI());
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(ticketType, cart);
        Ticket ticket = ticketService.chooseTicket(TicketClass
                .valueOf(ticketType.toUpperCase()), tour);
        nullCheck(ticket);
        cart.setTicket(ticket);
        //fix
        session.setAttribute(SessionParam.EXCURSIONS,
                excursionService.showCruiseExcursion(tour));
        return new Forward(URI.TICKET_JSP);
    }
}
