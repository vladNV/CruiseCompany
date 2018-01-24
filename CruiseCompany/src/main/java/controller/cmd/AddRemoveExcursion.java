package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.Cart;
import controller.util.RegexpParam;
import controller.util.URI;
import model.entity.Excursion;
import model.service.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestParser.*;

public class AddRemoveExcursion implements Action {
    private ExcursionService service;
    private static final String PARAM_ID = "id";
    private static final String PARAM_COMMAND = "command";
    private static final String CMD_REMOVE = "remove";
    private static final String CMD_ADD = "add";

    AddRemoveExcursion() {
        service = new ExcursionService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String excursion = request.getParameter(PARAM_ID);
        String command = request.getParameter(PARAM_COMMAND);
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(excursion, command, cart);
        validate(excursion, RegexpParam.NUMBER);
        validate(command, RegexpParam.EXCURSION_CMD);
        int excursionId = Integer.parseInt(excursion);
        Excursion ex = service.getExcursion(excursionId);
        nullCheck(ex);
        String answer;
        // TODO fix it
        if (command.equals(CMD_ADD)) {
            answer = cart.add(ex) ? "item.added" : "item.already.added";
        } else {
            answer = cart.remove(ex) ? "item.removed" : "item.already.removed";
        }
        request.setAttribute(RequestParam.EXCURSIONS, service
                .showCruiseExcursion(cart.getTicket().getTour().getId()));
        request.setAttribute(RequestParam.EXCURSION_STATUS, answer);
        return new Forward(URI.TICKET_JSP);
    }
}
