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
import futures.Param;
import futures.Verify;
import model.entity.Tour;
import model.service.ShipService;
import model.service.TicketService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.nullCheck;
import static controller.util.RequestUtil.validate;

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
        final Param ship = Param.newParam()
                .value(request.getParameter(PARAM_SHIP))
                .regexp(RegexpParam.NUMBER)
                .incorrect("incorrect.data").build();
        final Param type = Param.newParam()
                .values(request.getParameterValues(PARAM_TYPE))
                .regexp(RegexpParam.TYPE_PARAM)
                .incorrect("incorrect.data").build();
        final Param price = Param.newParam()
                .values(request.getParameterValues(PARAM_PRICE))
                .regexp(RegexpParam.PRICE)
                .incorrect("incorrect.data").build();
        final Param quantity = Param.newParam()
                .values(request.getParameterValues(PARAM_QUANTITY))
                .regexp(RegexpParam.LOCALE_DATE_TIME)
                .incorrect("incorrect.data").build();

        Verify verify = new Verify();
        if (verify.equalSize("incorrect.sizes" ,
                 type.getValues(), price.getValues(), quantity.getValues())
                .validate(ship).validateArray(type, price, quantity)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.ADD_TICKET_JSP);
        }
        Tour tour = builder.getTour();
        nullCheck(tour);
        int shipId = Integer.parseInt(ship.getValue());
        tour.setShip(serviceShip.selectShip(shipId));
        tour.setTickets(serviceTicket.extractTicket(price.getValues(),
                type.getValues(), quantity.getValues(),
                tour.getDeparture(), tour.getArrival()));
        serviceTour.createNewTour(tour);
        return new Redirect(URI.MAIN);
    }
}
