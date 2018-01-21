package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.Cart;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
import model.exceptions.ServiceException;
import model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class BuyTicket implements Action {
    private TicketService ticketService;
    private static final String CARD = "card";
    private static final String CVV = "cvv";
    private static final String MONEY = "money";

    BuyTicket() {ticketService = new TicketService(FactoryDAO
            .getDAOImpl(FactoryDAO.MYSQL));}

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String card = request.getParameter(CARD);
        String cvv = request.getParameter(CVV);
        String price = request.getParameter(MONEY);
        if (card == null || cvv == null
                || card.isEmpty() || cvv.isEmpty()
                    || price == null || price.isEmpty()) {
            return ActionResponse.Default();
        }
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        if (cart == null) return ActionResponse.Default();
        Ticket ticket = cart.getTicket();
        Set<Excursion> excursions = cart.getExcursions();
        User user = (User) session.getAttribute(SessionParam.USER);
        if (ticket == null || user == null) {
            return ActionResponse.Default();
        }
        long c, money;
        int CVV;
        try {
            c = Long.parseLong(card);
            CVV = Integer.parseInt(cvv);
            money = Long.parseLong(price);
        } catch (NumberFormatException e) {
            return ActionResponse.Default();
        }
        try {
            ticketService.buyTicket(ticket, excursions, user, money, c, CVV);
        } catch (ServiceException e) {
            request.setAttribute(RequestParam.CAUSE, e.getExceptionCause());
            return new ActionResponse(Act.FORWARD, URI.FAILED_PAYMENT_JSP);
        } catch (RuntimeException e) {
            return null;
        }
        return new ActionResponse(Act.FORWARD, URI.SUCCESS_PAYMENT_JSP);
    }
}
