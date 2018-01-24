package model.service;

import model.entity.Port;
import model.entity.Route;
import model.exceptions.RouteTimeException;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class RouteService {

    public LinkedList<Route> extractRoutes(String[] names, String[] dates,
                                           String[] port)
            throws RouteTimeException {
        LinkedList<Route> routes = new LinkedList<>();
        LocalDateTime start, finish;
        for (int i = 0; i < names.length - 1; i++) {
            start = LocalDateTime.parse(dates[i]);
            finish = LocalDateTime.parse(dates[i+1]);
            if (start.isAfter(finish)) throw new RouteTimeException();
            Port p = Port.newPort()
                    .id(Integer.parseInt(port[i]))
                    .build();
            routes.add(Route.newRoute()
                    .port(p)
                    .name(names[i])
                    .departure(start)
                    .arrival(finish)
                    .build());
        }
        return routes;
    }


}
