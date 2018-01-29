package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.TourBuilder;
import controller.util.URI;
import futures.Param;
import futures.Verify;
import model.entity.Route;
import model.entity.Tour;
import model.exceptions.RouteTimeException;
import model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.LinkedList;

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

        final Param name = Param.newParam()
                .value(request.getParameter(PARAM_NAME))
                .incorrect("incorrect.tourname")
                .regexp(RegexpParam.TOUR_NAME).build();

        final Param region = Param.newParam()
                .value(request.getParameter(PARAM_REGION))
                .incorrect("incorrect.region")
                .regexp(RegexpParam.REGION)
                .build();

        final Param routeNames = Param.newParam()
                .values(request.getParameterValues(PARAM_ROUTE_NAME))
                .incorrect("incorrect.routes")
                .regexp(RegexpParam.ROUTE)
                .build();

        final Param departures = Param.newParam()
                .values(request.getParameterValues(PARAM_DEPARTURE))
                .incorrect("incorrect.date")
                .regexp(RegexpParam.LOCALE_DATE_TIME)
                .build();

        final Param arrivals = Param.newParam()
                .values(request.getParameterValues(PARAM_ARRIVAL))
                .incorrect("incorrect.date")
                .regexp(RegexpParam.LOCALE_DATE_TIME)
                .build();

        final Param port = Param.newParam()
                .values(request.getParameterValues(PARAM_PORT))
                .incorrect("incorrect.port")
                .regexp(RegexpParam.NUMBER)
                .build();

        Verify verify = new Verify();
        if (!verify.equalSize("incorrect.sizes", routeNames.getValues(),
                departures.getValues(), arrivals.getValues(), port.getValues())
                .validate(name, region)
                .validateArray(port, departures, routeNames, arrivals)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }

        TourBuilder builder = (TourBuilder) session.getAttribute(SessionParam.BUILD_TOUR);
        LinkedList<Route> routes;
        try {
            routes = routeService.extractRoutes(routeNames.getValues(), departures.getValues(),
                                                arrivals.getValues(), port.getValues());
        } catch (RouteTimeException e) {
            request.setAttribute(RequestParam.WRONG, "incorrect.date");
            return forward;
        }
        Tour tour = Tour.newTour()
                .name(name.getValue())
                .region(region.getValue())
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
