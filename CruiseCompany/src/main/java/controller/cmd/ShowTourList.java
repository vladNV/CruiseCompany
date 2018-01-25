package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.RequestParser;
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
        int q = service.quantityOfPages(null);
        int page = RequestParser.getIdFromURI(request.getRequestURI());
        int maxPage = (q % TourService.LIMIT_TOUR == 0) ? q / TourService.LIMIT_TOUR :
                q / TourService.LIMIT_TOUR + 1;
        if (page > maxPage)
            throw new CommandException("so big page number");
        if (page == 0) {
            request.setAttribute(RequestParam.TOURS, service.showTours(1));
        } else {
            request.setAttribute(RequestParam.TOURS, service.showTours(page));
        }
        request.setAttribute(RequestParam.PAGE, maxPage);
        request.setAttribute(RequestParam.URL, "main");
        return new Forward(URI.MAIN_JSP);
    }
}
