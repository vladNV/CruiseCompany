package controller.cmd;

import controller.params.RequestParam;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.Cart;
import controller.util.URI;
import model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Confirm implements Action {
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_AMOUNT = "amount";

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String phone = request.getParameter(PARAM_PHONE);
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String amount = request.getParameter(PARAM_AMOUNT);
        if(phone == null || name == null || surname == null
                || amount == null || phone.isEmpty() || name.isEmpty()
                    || surname.isEmpty() || amount.isEmpty()) {
            return ActionResponse.Default();
        }
        int a; //amount
        try {
            a = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            return null;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) return ActionResponse.Default();
        Ticket ticket = cart.getTicket();
        ticket.setAmountPassengers(a);
        long price = cart.getPrice(a);
        request.setAttribute(RequestParam.PRICE, price);
        return new ActionResponse(Act.FORWARD, URI.PAYMENT_JSP);
    }
}
