package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.Cart;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;
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

    BuyTicket() {ticketService = new TicketService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));}

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
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) return ActionResponse.Default();
        Ticket ticket = cart.getTicket();
        Set<Excursion> excursions = cart.getExcursions();
        User user = (User) session.getAttribute("user");
        if (ticket == null || user == null) {
            return ActionResponse.Default();
        }
        boolean res = ticketService.buyTicket(ticket, excursions, user);
        return res ? new ActionResponse(Act.FORWARD, URI.SUCCESS_PAYMENT_JSP) :
                new ActionResponse(Act.FORWARD, URI.FAILED_PAYMENT_JSP);
    }
}
