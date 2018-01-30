package controller.cmd.user;

import controller.cmd.Action;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.TicketService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static controller.util.RequestUtil.nullCheck;

public class OpenTour implements Action {
    private TourService serviceTour;
    private TicketService serviceTicket;

    public OpenTour() {
        this.serviceTour = new TourService();
        this.serviceTicket = new TicketService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        int tourId = RequestUtil.getIdFromURI(request.getRequestURI());
        Tour tour = serviceTour.allInformationAboutTour(tourId);
        List<Ticket> tickets = serviceTicket.showTicketsForTour(tourId);
        nullCheck(tour);
        tour.setTickets(tickets);
        request.setAttribute(RequestParam.TOUR, tour);
        return new Forward(URI.TOUR_PAGE_JSP);
    }

}
