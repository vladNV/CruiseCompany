package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Ticket;
import model.service.ExcursionService;
import model.service.TicketService;

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
        int ticketId = RequestParser.getIdFromURI(request.getRequestURI());
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(cart);
        Ticket ticket = ticketService.chooseTicket(ticketId);
        nullCheck(ticket);
        cart.setTicket(ticket);
        request.setAttribute(RequestParam.EXCURSIONS,
                excursionService.showCruiseExcursion(ticket.getTour().getId()));
        return new Forward(URI.TICKET_JSP);
    }
}
