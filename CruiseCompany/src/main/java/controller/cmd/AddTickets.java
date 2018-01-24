package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.Servlet;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.RegexpURI;
import controller.util.URI;
import model.entity.Tour;
import model.service.ShipService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class AddTickets implements Action {
    private ShipService serviceShip;
    private TourService serviceTour;
    private static final String  PARAM_TYPE = "type";
    private static final String  PARAM_PRICE = "type";
    private static final String  PARAM_QUANTITY = "type";
    private static final String  PARAM_SHIP = "ship";

    AddTickets() {
        serviceShip = new ShipService();
        serviceTour = new TourService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        Forward forward = new Forward(URI.ADD_TICKET_JSP);
        if (request.getMethod().equals(Servlet.GET)) {
            request.setAttribute("ships", serviceShip.selectShips());
            return forward;
        }
        HttpSession session = request.getSession();
        String ship = request.getParameter(PARAM_SHIP);
        String type[] = request.getParameterValues(PARAM_TYPE);
        String price[] = request.getParameterValues(PARAM_PRICE);
        String quantity[] = request.getParameterValues(PARAM_QUANTITY);
        Tour tour = (Tour) session.getAttribute(SessionParam.BUILD_TOUR);
        nullCheck(type, price, quantity, tour, ship);
        for (int i = 0; i < type.length; i++) {
            nullCheck(type[i], price[i], quantity[i]);
        }
        if (validate(type, RegexpURI.TICKET_TYPE) && validate(price, RegexpParam.PRICE)
                && validate(quantity, RegexpParam.NUMBER)  && validate(ship, RegexpParam.NUMBER)) {
            request.setAttribute(RequestParam.WRONG, "incorrect.data");
            return forward;
        }
        int shipId = Integer.parseInt(ship);
        tour.setShip(serviceShip.selectShip(shipId));
        serviceTour.createNewTour(tour);
        return new Redirect(URI.MAIN);
    }
}
