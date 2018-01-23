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

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class AddExcursion implements Action {
    private ExcursionService service;
    private static final String PARAM_ID = "id";

    AddExcursion() {
        service = new ExcursionService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        String excursion = request.getParameter(PARAM_ID);
        Cart cart = (Cart) session.getAttribute(SessionParam.CART);
        nullCheck(excursion, cart);
        validate(excursion, RegexpParam.NUMBER);
        int excursionId = Integer.parseInt(excursion);
        Excursion ex = service.getExcursion(excursionId);
        nullCheck(ex);
        String answer = cart.add(ex) ? "item.added" : "item.already.added";
        request.setAttribute(RequestParam.EXCURSION_STATUS, answer);
        return new Forward(URI.TICKET_JSP);
    }
}
