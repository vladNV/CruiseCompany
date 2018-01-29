package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.Regexp;
import controller.util.TourBuilder;
import controller.util.URI;
import futures.Verify;
import model.entity.Tour;
import model.service.ShipService;
import model.service.TicketService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.nullArrayCheck;
import static controller.util.RequestUtil.nullCheck;

public class AddTickets implements Action {
    private ShipService serviceShip;
    private TourService serviceTour;
    private TicketService serviceTicket;
    private static final String  PARAM_TYPE = "type";
    private static final String  PARAM_PRICE = "price";
    private static final String  PARAM_QUANTITY = "quantity";
    private static final String  PARAM_SHIP = "ship";

    AddTickets() {
        serviceShip = new ShipService();
        serviceTour = new TourService();
        serviceTicket = new TicketService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        TourBuilder builder = (TourBuilder) session.getAttribute(SessionParam.BUILD_TOUR);
        final String ship = request.getParameter(PARAM_SHIP);
        final String type[] = request.getParameterValues(PARAM_TYPE);
        final String price[] = request.getParameterValues(PARAM_PRICE);
        final String quantity[] = request.getParameterValues(PARAM_QUANTITY);
        nullCheck(ship);
        nullArrayCheck(type, price, quantity);
        Verify verify = new Verify();
        if (!verify.incorrect("incorrect.data")
                .regexp(Regexp.NUMBER).validate(ship)
                .regexp(Regexp.TYPE_PARAM).validateArray(type)
                .regexp(Regexp.PRICE).validateArray(price)
                .regexp( Regexp.QUANTITY).validateArray(quantity).allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.ADD_TICKET_JSP);
        }
        Tour tour = builder.getTour();
        nullCheck(tour);
        int shipId = Integer.parseInt(ship);
        tour.setShip(serviceShip.selectShip(shipId));
        tour.setTickets(serviceTicket.extractTicket(price, type, quantity,
                tour.getDeparture(), tour.getArrival()));
        serviceTour.createNewTour(tour);
        return new Redirect(URI.MAIN);
    }
}
