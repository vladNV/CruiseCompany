package controller.cmd;

import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.URI;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTourList implements Action {

    private final TourService service;

    ShowTourList() {
        service = new TourService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        request.setAttribute(RequestParam.TOURS, service.showTours());
        return new Forward(URI.MAIN_JSP);
    }
}
