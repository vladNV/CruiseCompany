package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.entity.Ticket;
import model.entity.Tour;
import model.service.ExcursionService;
import model.service.TicketService;
import model.entity.TicketClass;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;

public class ChooseTicket implements Action {
    private TicketService ticketService;
    private ExcursionService excursionService;
    private TourService tourService;

    ChooseTicket() {
        ticketService = new TicketService();
        excursionService = new ExcursionService();
        tourService = new TourService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                   HttpServletResponse response) {
        HttpSession session = request.getSession();
        String ticketType = RequestParser.getTicketType(request.getRequestURI());
        int tourId = RequestParser.getIdFromURI(request.getRequestURI());
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(ticketType, cart);
        Ticket ticket = ticketService.chooseTicket(TicketClass
                .valueOf(ticketType.toUpperCase()), tourId);
        Tour tour = tourService.allInformationAboutTour(tourId);
        nullCheck(ticket, tour);
        ticket.setTour(tour);
        cart.setTicket(ticket);
        request.setAttribute(RequestParam.EXCURSIONS,
                excursionService.showCruiseExcursion(tourId));
        return new Forward(URI.TICKET_JSP);
    }
}
