package controller.cmd;

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
        service = new ExcursionService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String excursion = request.getParameter(PARAM_ID);
        Cart cart = (Cart) session.getAttribute("cart");
        int excursionId;
        try {
            excursionId = Integer.parseInt(excursion);
        } catch (NumberFormatException e) {
            return ActionResponse.Default();
        }
        if (cart == null || excursionId == 0) return ActionResponse.Default();
        Excursion ex = service.getExcursion(excursionId);
        if (ex == null) return ActionResponse.Default();
        String answer = cart.remove(ex) ?
                "item.removed" : "item.already.removed";
        session.setAttribute("excursionStatus", answer);
        return new ActionResponse(Act.FORWARD, URI.TICKET_JSP);
    }
}
