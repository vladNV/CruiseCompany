package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.*;
import model.dao.FactoryDAO;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.TicketService;
import model.service.TourService;
import model.dao.mapper.AggregateOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static controller.util.RequestParser.nullCheck;

public class OpenTour implements Action {
    private TourService serviceTour;
    private TicketService serviceTicket;

    OpenTour() {
        this.serviceTour = new TourService();
        this.serviceTicket = new TicketService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        int tourId = RequestParser.getIdFromURI(request.getRequestURI());
        Tour tour = serviceTour.allInformationAboutTour(tourId);
        List<AggregateOperation<Integer, Ticket>> ticketCategories =
                serviceTicket.amountTicket(tourId);
        nullCheck(tour);
        request.setAttribute("tour", tour);
        request.setAttribute(RequestParam.TOUR_TICKETS, ticketCategories);
        return new Forward(URI.TOUR_PAGE_JSP);
    }

}
