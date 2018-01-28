package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import futures.Param;
import futures.Verify;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static controller.util.RequestParser.nullCheck;

public class BuyTicket implements Action {
    private TicketService ticketService;
    private static final String PARAM_CARD = "card";
    private static final String PARAM_CVV = "cvv";
    private static final String PARAM_PRICE = "price";

    BuyTicket() {ticketService = new TicketService();}

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        Param card = new Param();
        card.setValue(request.getParameter(PARAM_CARD));
        card.setIncorrect("incorrect.card");
        card.setRegexp(RegexpParam.NUMBER);

        Param cvv = new Param();
        cvv.setValue(request.getParameter(PARAM_CVV));
        cvv.setIncorrect("incorrect.cvv");
        cvv.setRegexp(RegexpParam.CVV);

        Param price = new Param();
        price.setValue(request.getParameter(PARAM_PRICE));
        price.setRegexp(RegexpParam.PRICE);
        price.setIncorrect("incorrect.price");

        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        User user = (User) session.getAttribute(SessionParam.USER);
        nullCheck(user, cart);

        Ticket ticket = cart.getTicket();
        Set<Excursion> excursions = cart.getExcursions();
        nullCheck(ticket);

        Verify verify = new Verify();

        if (verify.validate(card).validate(price).validate(cvv).allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.PAYMENT_JSP);
        }

        long c = card.toLong();
        int CVV = cvv.toInt();
        long money = price.toLong();
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
