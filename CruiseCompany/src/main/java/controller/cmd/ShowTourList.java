package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.RequestUtil;
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
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        int q = service.quantityOfPages(null);
        int page = RequestUtil.getIdFromURI(request.getRequestURI());
        int maxPage = RequestUtil.getPage(q, page);
        putTours(request, page);
        request.setAttribute(RequestParam.PAGE, maxPage);
        request.setAttribute(RequestParam.URL, "main");
        return new Forward(URI.MAIN_JSP);
    }

    private void putTours(HttpServletRequest request, int page) {
        if (page == 0) {
            request.setAttribute(RequestParam.TOURS, service.showTours(1));
        } else {
            request.setAttribute(RequestParam.TOURS, service.showTours(page));
        }
    }


}
