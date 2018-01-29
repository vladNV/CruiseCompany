package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.Cart;
import controller.util.Regexp;
import controller.util.URI;
import futures.Verify;
import model.entity.Excursion;
import model.service.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.*;

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
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        Forward forward = new Forward(URI.TICKET_JSP);
        final String excursion = request.getParameter(request.getParameter(PARAM_ID));
        final String command = request.getParameter(PARAM_COMMAND);
        nullCheck(excursion, command);
        Verify verify = new Verify();
        if (!verify.incorrect("incorrect.data")
                .regexp(Regexp.NUMBER).validate(excursion)
                .regexp(Regexp.EXCURSION_CMD).validate(command)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        int excursionId = Integer.parseInt(excursion);
        Excursion ex = service.getExcursion(excursionId);
        nullCheck(cart, ex);
        request.setAttribute(RequestParam.EXCURSION_STATUS,
                excursionCommand(command, cart, ex));
        return forward;
    }

    private String excursionCommand(String command, Cart cart, Excursion ex) {
        switch (command) {
            case CMD_ADD:
                return cart.add(ex) ? "item.added" : "item.already.added";
            case CMD_REMOVE:
                return cart.remove(ex) ? "item.removed" : "item.already.removed";
            default:
                throw new CommandException("unknown command");
        }
    }
}
