package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static controller.util.RequestParser.isNull;
import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class BuyTicket implements Action {
    private TicketService ticketService;
    private static final String CARD = "card";
    private static final String CVV = "cvv";
    private static final String MONEY = "money";

    BuyTicket() {ticketService = new TicketService();}

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        String card = request.getParameter(CARD);
        String cvv = request.getParameter(CVV);
        String price = request.getParameter(MONEY);
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        User user = (User) session.getAttribute(SessionParam.USER);
        nullCheck(card, cvv, price, cart, user);
        Ticket ticket = cart.getTicket();
        Set<Excursion> excursions = cart.getExcursions();
        nullCheck(ticket);
        if (!validate(card, RegexpParam.NUMBER) || !validate(cvv, RegexpParam.CVV) ||
                !validate(price, RegexpParam.PRICE)) {
            request.setAttribute(RequestParam.WRONG, "incorrect.data");
            return new Forward(URI.PAYMENT_JSP);
        }
        long c = Long.parseLong(card);
        int CVV = Integer.parseInt(cvv);
        long money = Long.parseLong(price);
        try {
            ticketService.buyTicket(ticket, excursions, user, money, c, CVV);
        } catch (ServiceException e) {
            request.setAttribute(RequestParam.CAUSE, e.getExceptionCause());
            return new Forward(URI.FAILED_PAYMENT_JSP);
        }
        cart.clear();
        return new Forward(URI.SUCCESS_PAYMENT_JSP);
    }
}
