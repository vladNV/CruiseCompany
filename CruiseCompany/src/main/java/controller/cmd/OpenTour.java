package controller.cmd;

import controller.params.RequestParam;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.RequestParser;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.TicketService;
import model.service.TourService;
import model.util.AggregateOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenTour implements Action {
    private TourService serviceTour;
    private TicketService serviceTicket;

    OpenTour() {
        this.serviceTour = new TourService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
        this.serviceTicket = new TicketService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        int tourId = RequestParser.getIdFromURI(uri);
        if (tourId == 0) return new ActionResponse(Act.REDIRECT, URI.MAIN);
        Tour tour = serviceTour.allInformationAboutTour(tourId);
        List<AggregateOperation<Integer, Ticket>> ticketCategories =
                serviceTicket.amountTicket(tourId);
        if (tour == null) return ActionResponse.Default();
        // tour session ?
        session.setAttribute("tour", tour);
        request.setAttribute(RequestParam.TOUR_TICKETS, ticketCategories);
        return new ActionResponse(Act.FORWARD, URI.TOUR_PAGE_JSP);
    }

}
