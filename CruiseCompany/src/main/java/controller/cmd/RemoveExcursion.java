package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.*;
import model.dao.FactoryDAO;
import model.entity.Excursion;
import model.service.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveExcursion implements Action {
    private ExcursionService service;
    private static final String PARAM_ID = "id";

    RemoveExcursion() {
        service = new ExcursionService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        String excursion = request.getParameter(PARAM_ID);
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        if (cart == null || excursion== null
                || !excursion.matches(RegexpParam.NUMBER))
            throw new CommandException("cart is null");
        int excursionId = Integer.parseInt(excursion);
        Excursion ex = service.getExcursion(excursionId);
        if (ex == null) throw new CommandException("ex is null");
        String answer = cart.remove(ex) ?
                "item.removed" : "item.already.removed";
        request.setAttribute(RequestParam.EXCURSION_STATUS, answer);
        return new Forward(URI.TICKET_JSP);
    }
}
