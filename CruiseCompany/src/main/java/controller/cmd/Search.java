package controller.cmd;

import controller.exceptions.CommandException;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.RequestParser;
import controller.util.URI;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.util.RequestParser.nullCheck;
import static controller.util.RequestParser.validate;

public class Search implements Action {
    private static final String PARAM_SEARCH = "search";
    private TourService service;

    public Search() {
        this.service = new TourService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter(PARAM_SEARCH);
        nullCheck(search);
        if (validate(search, RegexpParam.TOUR_NAME)) {
            return new Redirect(URI.MAIN);
        }
        int q = service.quantityOfPages(search);
        int maxPage = (q % TourService.LIMIT_TOUR == 0) ? q / TourService.LIMIT_TOUR :
                q / TourService.LIMIT_TOUR + 1;
        int page = RequestParser.getIdFromURI(request.getRequestURI());
        if (page > maxPage)
            throw new CommandException("so big page number");
        if (page == 0) {
            request.setAttribute(RequestParam.TOURS,
                    service.searchTourForRegion(search, 1));
        } else {
            request.setAttribute(RequestParam.TOURS,
                    service.searchTourForRegion(search, page));
        }
        request.setAttribute(RequestParam.URL, "search");
        request.setAttribute(RequestParam.PAGE, maxPage);
        return new Forward(URI.MAIN_JSP);
    }
}
