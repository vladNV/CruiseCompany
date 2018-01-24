package controller.cmd;

import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.URI;
import model.service.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowExcursionList implements Action {

    private ExcursionService service;

    ShowExcursionList() {
        this.service = new ExcursionService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        request.setAttribute(RequestParam.EXCURSIONS, service.showExcursions());
        return new Forward(URI.EXCURSIONS_JSP);
    }
}
