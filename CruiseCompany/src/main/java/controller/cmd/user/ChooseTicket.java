package controller.cmd.user;

import controller.cmd.Action;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Cart;
import model.entity.Ticket;
import model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.nullCheck;

public class ChooseTicket implements Action {
    private TicketService ticketService;

    public ChooseTicket() {
        ticketService = new TicketService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                   final HttpServletResponse response) {
        HttpSession session = request.getSession();
        int ticketId = RequestUtil.getIdFromURI(request.getRequestURI());
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        Ticket ticket = ticketService.chooseTicket(ticketId);
        nullCheck(ticket, cart);
        cart.setTicket(ticket);
        return new Forward(URI.TICKET_JSP);
    }
}
