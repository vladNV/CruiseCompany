package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.URI;
import model.entity.Ticket;
import model.entity.User;
import model.service.TicketService;
import model.entity.Tuple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static controller.util.RequestUtil.nullCheck;

public class ShowUserTickets implements Action {
    private TicketService service;

    ShowUserTickets() {
        service = new TicketService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionParam.USER);
        nullCheck(user);
        Tuple<List<Ticket>, List<Ticket>> t = service
                .userTickets(user, LocalDateTime.now());
        request.setAttribute(RequestParam.ACTIVE_TICKETS, t.x());
        request.setAttribute(RequestParam.OLD_TICKETS, t.y());
        return new Forward(URI.PROFILE_JSP);
    }
}
