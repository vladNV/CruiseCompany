package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import futures.Verify;
import model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.nullCheck;

public class Confirm implements Action {
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_AMOUNT = "amount";

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        String phone = request.getParameter(PARAM_PHONE);
        String name = request.getParameter(PARAM_NAME);
        String amount = request.getParameter(PARAM_AMOUNT);
        String surname = request.getParameter(PARAM_SURNAME);
        nullCheck(phone, name, amount, surname);
        Verify verify = new Verify();
        if (!verify
                .incorrect("incorrect.phone").regexp(Regexp.PHONE).validate(phone)
                .incorrect("incorrect.name").regexp(Regexp.NAME)
                .validate(name).validate(surname)
                .incorrect("incorrect.amount").regexp(Regexp.NUMBER).validate(amount)
                .allRight()) {
            request.getSession().setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.TICKET_JSP);
        }
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(cart);
        int quantity = Integer.parseInt(amount);
        Ticket ticket = cart.getTicket();
        ticket.setAmountPassengers(quantity);
        ticket.setPerson(name+ " " + surname);
        request.setAttribute(RequestParam.PRICE,  cart.getPrice(quantity));
        return new Forward(URI.PAYMENT_JSP);
    }
}
