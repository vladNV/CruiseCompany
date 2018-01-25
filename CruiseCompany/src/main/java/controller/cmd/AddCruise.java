package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.MessageManager;
import controller.util.RegexpParam;
import controller.util.TourBuilder;
import controller.util.URI;
import model.entity.Route;
import model.entity.Tour;
import model.exceptions.RouteTimeException;
import model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.LinkedList;

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class AddCruise implements Action {
    private RouteService routeService;
    private ShipService serviceShip;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DEPARTURE = "departure";
    private static final String PARAM_ARRIVAL = "arrival";
    private static final String PARAM_REGION = "region";
    private static final String PARAM_ROUTE_NAME = "routeName";
    private static final String PARAM_PORT = "port";

    AddCruise() {
        routeService = new RouteService();
        serviceShip = new ShipService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        Forward forward = new Forward(URI.ADD_CRUISE_JSP);
        HttpSession session = request.getSession();

        String name = request.getParameter(PARAM_NAME);
        String region = request.getParameter(PARAM_REGION);
        String[] routeNames = request.getParameterValues(PARAM_ROUTE_NAME);
        String[] departures = request.getParameterValues(PARAM_DEPARTURE);
        String[] arrivals = request.getParameterValues(PARAM_ARRIVAL);
        String[] port = request.getParameterValues(PARAM_PORT);
        TourBuilder builder = (TourBuilder) session.getAttribute(SessionParam.BUILD_TOUR);

        nullCheck(name, region, routeNames, departures, arrivals, port, builder);
        // check array length
        String status;
        if (!(status = validateParam(name, region, port, departures, arrivals, routeNames))
                .isEmpty()) {
            request.setAttribute(RequestParam.WRONG, status);
            return forward;
        }
        LinkedList<Route> routes;
        try {
            routes = routeService.extractRoutes(routeNames, departures, arrivals, port);
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

    private String validateParam(String name, String region, String[] port,
                                 String[] departures, String[] arrivals,
                                 String[] names) {
        String status = "";
        if (!validate(name, RegexpParam.TOUR_NAME)) {
            status += MessageManager.getMessage("incorrect.tourname") + "\n";
        }
        if (!validate(region, RegexpParam.NAME)) {
            status += MessageManager.getMessage("incorrect.region") + "\n";
        }
        if (!validate(names, RegexpParam.TOUR_NAME)) {
            status += MessageManager.getMessage("incorrect.routes") + "\n";
        }
        if (!validate(departures, RegexpParam.LOCALE_DATE_TIME)) {
            status += MessageManager.getMessage("incorrect.date") + "\n";
        }
        if (!validate(arrivals, RegexpParam.LOCALE_DATE_TIME)) {
            status += MessageManager.getMessage("incorrect.date") + "\n";
        }
        if (!validate(port, RegexpParam.NUMBER)) {
            status += MessageManager.getMessage("incorrect.port") + "\n";
        }
        return status;
    }
}
