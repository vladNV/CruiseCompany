package controller.cmd.user;

import controller.cmd.Action;
import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import controller.util.Verify;
import model.entity.Cart;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static controller.util.RequestUtil.nullCheck;

public class BuyTicket implements Action {
    private TicketService ticketService;
    private static final String PARAM_CARD = "card";
    private static final String PARAM_CVV = "cvv";
    private static final String PARAM_PRICE = "price";

    public BuyTicket() {ticketService = new TicketService();}

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        User user = (User) session.getAttribute(SessionParam.USER);
        String card = request.getParameter(PARAM_CARD);
        String cvv = request.getParameter(PARAM_CVV);
        String price = request.getParameter(PARAM_PRICE);
        nullCheck(card, cvv, price, user, cart);
        Verify verify = new Verify();
        if (!verify
                .incorrect("incorrect.card").regexp(Regexp.NUMBER).validate(card)
                .incorrect("incorrect.cvv").regexp(Regexp.CVV).validate(cvv)
                .incorrect("incorrect.price").regexp(Regexp.PRICE).validate(price)
            .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.PAYMENT_JSP);
        }
        Ticket ticket = cart.getTicket();
        Set<Excursion> excursions = cart.getExcursions();
        nullCheck(ticket);
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
