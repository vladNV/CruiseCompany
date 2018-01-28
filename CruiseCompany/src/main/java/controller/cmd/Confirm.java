package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import futures.Param;
import futures.Verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;

public class Confirm implements Action {
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_AMOUNT = "amount";

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        Param phone = new Param();
        phone.setValue(request.getParameter(PARAM_PHONE));
        phone.setIncorrect("incorrect.phone");
        phone.setRegexp(RegexpParam.PHONE);

        Param name = new Param();
        name.setValue(request.getParameter(PARAM_NAME));
        name.setRegexp(RegexpParam.NAME);
        name.setIncorrect("incorrect.name");

        Param surname  = new Param();
        surname.setValue(request.getParameter(PARAM_SURNAME));
        surname.setIncorrect("incorrect.name");
        surname.setRegexp(RegexpParam.NAME);

        Param amount = new Param();
        amount.setValue(request.getParameter(PARAM_AMOUNT));
        amount.setIncorrect("incorrect.amount");
        amount.setRegexp(RegexpParam.NUMBER);

        Verify verify = new Verify();
        if (verify.validate(name)
                .validate(surname)
                .validate(phone)
                .validate(amount)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.TICKET_JSP);
        }
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(cart);
        int quantity = amount.toInt();
        cart.getTicket().setAmountPassengers(quantity);
        request.setAttribute(RequestParam.PRICE,  cart.getPrice(quantity));
        return new Forward(URI.PAYMENT_JSP);
    }
}
