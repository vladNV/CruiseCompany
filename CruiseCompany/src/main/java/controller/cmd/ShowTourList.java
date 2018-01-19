package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.Tour;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowTourList implements Action {

    private final TourService service;

    ShowTourList() {
        service = new TourService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle res = ResourceBundle.getBundle("messages", locale);
        request.setAttribute("tours", service.showTours());
        return new ActionResponse(Act.FORWARD, URI.MAIN_JSP);
    }
}