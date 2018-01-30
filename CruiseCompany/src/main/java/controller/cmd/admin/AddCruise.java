package controller.cmd.admin;

import controller.cmd.Action;
import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.Regexp;
import model.entity.TourBuilder;
import controller.util.URI;
import controller.util.Verify;
import model.entity.Route;
import model.entity.Tour;
import model.exceptions.RouteTimeException;
import model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.LinkedList;

import static controller.util.RequestUtil.nullArrayCheck;
import static controller.util.RequestUtil.nullCheck;

public class AddCruise implements Action {
    private RouteService routeService;
    private ShipService serviceShip;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DEPARTURE = "departure";
    private static final String PARAM_ARRIVAL = "arrival";
    private static final String PARAM_REGION = "region";
    private static final String PARAM_ROUTE_NAME = "routeName";
    private static final String PARAM_PORT = "port";

    public AddCruise() {
        routeService = new RouteService();
        serviceShip = new ShipService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                final HttpServletResponse response) {
        Forward forward = new Forward(URI.ADD_CRUISE_JSP);
        HttpSession session = request.getSession();
        String name = request.getParameter(PARAM_NAME);
        String region = request.getParameter(PARAM_REGION);
        String[] routeNames = request.getParameterValues(PARAM_ROUTE_NAME);
        String[] arrivals = request.getParameterValues(PARAM_ARRIVAL);
        String[] departures = request.getParameterValues(PARAM_DEPARTURE);
        String[] ports = request.getParameterValues(PARAM_PORT);
        nullCheck(name, region);
        nullArrayCheck(routeNames, arrivals, departures, ports);
        Verify verify = new Verify();
        if (!verify.incorrect("incorrect.sizes")
                .equalSize(routeNames, arrivals, departures, ports)
                .regexp(Regexp.TOUR_NAME).incorrect("incorrect.tourname").validate(name)
                .regexp(Regexp.REGION).incorrect("incorrect.region").validate(region)
                .regexp(Regexp.LOCALE_DATE_TIME).incorrect("incorrect.date")
                .validateArray(departures).validateArray(arrivals)
                .regexp(Regexp.ROUTE).incorrect("incorrect.routes")
                .validateArray(routeNames)
                .regexp(Regexp.NUMBER).incorrect("incorrect.port")
                .validateArray(ports).allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }
        TourBuilder builder = (TourBuilder) session.getAttribute(SessionParam.BUILD_TOUR);
        LinkedList<Route> routes;
        try {
            routes = routeService.extractRoutes(routeNames, departures, arrivals, ports);
        } catch (RouteTimeException e) {
            request.setAttribute(RequestParam.WRONG, "incorrect.date");
            return forward;
        }
        Tour tour = Tour.newTour()
                .name(name)
                .region(region)
                .departure(routes.getFirst().getDeparture())
                .arrival(routes.getLast().getArrival())
                .routes(routes)
                .build();
        builder.setTour(tour);
        builder.setShips(serviceShip.selectFreeShips(tour.getDeparture(),
                tour.getArrival()));
        return new Forward(URI.ADD_TICKET_JSP);
    }
}
