package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class Confirm implements Action {
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_AMOUNT = "amount";

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        String phone = request.getParameter(PARAM_PHONE);
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String amount = request.getParameter(PARAM_AMOUNT);
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(cart, phone, name, surname, amount);
        validate(name, RegexpParam.NAME);
        int quantity = Integer.parseInt(amount);
        cart.getTicket().setAmountPassengers(quantity);
        request.setAttribute(RequestParam.PRICE,  cart.getPrice(quantity));
        return new Forward(URI.PAYMENT_JSP);
    }
}
