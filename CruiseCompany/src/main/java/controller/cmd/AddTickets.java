package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.RegexpURI;
import controller.util.TourBuilder;
import controller.util.URI;
import model.entity.Tour;
import model.service.ShipService;
import model.service.TicketService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

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
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        TourBuilder builder = (TourBuilder) session.getAttribute(SessionParam.BUILD_TOUR);
        String ship = request.getParameter(PARAM_SHIP);
        String type[] = request.getParameterValues(PARAM_TYPE);
        String price[] = request.getParameterValues(PARAM_PRICE);
        String quantity[] = request.getParameterValues(PARAM_QUANTITY);
        nullCheck(type, price, quantity, ship, builder);
        if (!validate(type, RegexpURI.TICKET_TYPE) && !validate(price, RegexpParam.PRICE)
                && !validate(quantity, RegexpParam.NUMBER)  && !validate(ship, RegexpParam.NUMBER)) {
            request.setAttribute(RequestParam.WRONG, "incorrect.data");
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
