package model.service;

import model.entity.Port;
import model.entity.Route;
import model.exceptions.RouteTimeException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class RouteService {
    private final static Logger logger = Logger.getLogger(RouteService.class);

    public LinkedList<Route> extractRoutes(String[] names, String[] departures,
                                           String arrivals[], String[] port)
            throws RouteTimeException {
        logger.info("extract routes ");
        LinkedList<Route> routes = new LinkedList<>();
        LocalDateTime departure, arrival;
        for (int i = 0; i < names.length; i++) {
            departure = LocalDateTime.parse(departures[i]);
            arrival = LocalDateTime.parse(arrivals[i]);
            if (departure.isAfter(arrival)) throw new RouteTimeException();
            Port p = Port.newPort()
                    .id(Integer.parseInt(port[i]))
                    .build();
            routes.add(Route.newRoute()
                    .port(p)
                    .name(names[i])
                    .departure(departure)
                    .arrival(arrival)
                    .build());
        }
        return routes;
    }


}
