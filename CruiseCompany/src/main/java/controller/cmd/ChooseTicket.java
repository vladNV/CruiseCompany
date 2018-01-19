package controller.cmd;

import controller.regexp.RegexpURI;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.ExcursionService;
import model.service.TicketService;
import model.util.TicketClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseTicket implements Action {
    private TicketService ticketService;
    private ExcursionService excursionService;

    ChooseTicket() {
        ticketService = new TicketService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
        excursionService = new ExcursionService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String ticketType = getTicketType(request.getRequestURI());
        if (ticketType == null || ticketType.isEmpty())
            return new ActionResponse(Act.NONE, "");
        Ticket ticket = ticketService.chooseTicket(TicketClass
                .valueOf(ticketType.toUpperCase()));
        Tour tour = (Tour) session.getAttribute("tour");
        if (ticket == null || tour == null)
            return new ActionResponse(Act.NONE, "");
        session.setAttribute("ticket", ticket);
        request.setAttribute("excursions",
                excursionService.showCruiseExcursion(tour.getId()));
        return new ActionResponse(Act.FORWARD, URI.TICKET_JSP);
    }

    private String getTicketType(String uri) {
        String ticketType = "";
        Pattern pattern = Pattern.compile(RegexpURI.TICKET_TYPE);
        Matcher matcher = pattern.matcher(uri);
        while (matcher.find()) {
            ticketType = matcher.group();
        }
        return ticketType;
    }
}
