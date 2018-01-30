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
import model.entity.*;
import model.service.ShipService;
import model.service.TicketService;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static controller.util.RequestUtil.nullArrayCheck;
import static controller.util.RequestUtil.nullCheck;

public class AddTickets implements Action {
    private ShipService serviceShip;
    private TourService serviceTour;
    private TicketService serviceTicket;
    private static final String PARAM_PRICE = "price";
    private static final String PARAM_BONUS = "bonus";
    private static final String PARAM_SHIP = "ship";

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
        final String price[] = request.getParameterValues(PARAM_PRICE);
        final String bonus[] = request.getParameterValues(PARAM_BONUS);
        nullCheck(ship);
        nullArrayCheck(price, bonus);
        Verify verify = new Verify();
        if (!verify.incorrect("incorrect.data")
                .regexp(Regexp.NUMBER).validate(ship)
                .regexp(Regexp.PRICE).validateArray(price)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return new Forward(URI.ADD_TICKET_JSP);
        }
        Tour tour = builder.getTour();
        nullCheck(tour);
        int shipId = Integer.parseInt(ship);
        tour.setShip(serviceShip.selectShip(shipId));
        int capacity = tour.getShip().getCapacity();
        tour.setTickets(serviceTicket
                .extractTicket(price, extractBonus(bonus), countAmount(capacity)));
        serviceTour.createNewTour(tour);
        return new Redirect(URI.MAIN);
    }

    private HashMap<TicketClass, List<TicketBonus>> extractBonus(String bonus[]) {
        HashMap<TicketClass, List<TicketBonus>> map = new HashMap<>();
        Tuple<TicketClass, TicketBonus> tuple;
        for (String s : bonus) {
            tuple = split(s.split(","));
            map.computeIfAbsent(tuple.x(), v -> new ArrayList<>()).add(tuple.y());
        }
        return map;
    }

    private Tuple<TicketClass, TicketBonus> split(String[] value) {
        return new Tuple<>(
                TicketClass.valueOf(value[0].toUpperCase()),
                TicketBonus.valueOf(value[1].toUpperCase())
        );
    }

    private int[] countAmount(int capacity) {
        TicketClass[] types = TicketClass.values();
        int[] amount = new int[types.length];
        for (int i = 0; i < amount.length; i++) {
            amount[i] = (int)(capacity * types[i].getRelative());
        }
        return amount;
    }
}
