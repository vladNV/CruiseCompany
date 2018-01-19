package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.TicketService;
import model.service.TourService;
import model.util.AggregateOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String uri = request.getRequestURI();
        int tourId = getTourIdFromURI(uri);
        if (tourId == 0) return new ActionResponse(Act.REDIRECT, URI.MAIN);
        Tour tour = serviceTour.allInformationAboutTour(tourId);
        List<AggregateOperation<Integer, Ticket>> ticketCategories =
                serviceTicket.amountTicket(tourId);
        if (tour == null) return new ActionResponse(Act.NONE, "");
        request.setAttribute("tour", tour);
        request.setAttribute("tour_tickets", ticketCategories);
        return new ActionResponse(Act.FORWARD, URI.TOUR_PAGE_JSP);
    }

    private int getTourIdFromURI(String URI) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(URI);
        int id;
        String m = "";
        while (matcher.find()) {
            m = matcher.group();
        }
        try {
            id = Integer.parseInt(m);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return id;
    }
}
