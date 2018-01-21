package controller.cmd;

import controller.params.RequestParam;
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
        request.setAttribute(RequestParam.TOURS, service.showTours());
        return new ActionResponse(Act.FORWARD, URI.MAIN_JSP);
    }
}
