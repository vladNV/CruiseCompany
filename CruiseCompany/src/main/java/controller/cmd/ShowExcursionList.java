package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.Excursion;
import model.service.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowExcursionList implements Action {

    private ExcursionService service;

    ShowExcursionList() {
        this.service = new ExcursionService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        request.setAttribute("excursions", service.showExcursions());
        return new ActionResponse(Act.FORWARD, URI.EXCURSIONS_JSP);
    }
}
