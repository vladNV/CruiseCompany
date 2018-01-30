package controller.cmd.user;

import controller.cmd.Action;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.Regexp;
import controller.util.RequestUtil;
import controller.util.URI;
import model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.util.RequestUtil.nullCheck;
import static controller.util.RequestUtil.validate;

public class Search implements Action {
    private static final String PARAM_SEARCH = "search";
    private TourService service;

    public Search() {
        this.service = new TourService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        String search = request.getParameter(PARAM_SEARCH);
        nullCheck(search);
        if (validate(search, Regexp.TOUR_NAME)) {
            return new Redirect(URI.MAIN);
        }
        int q = service.quantityOfPages(search);
        int page = RequestUtil.getIdFromURI(request.getRequestURI());
        int maxPage = RequestUtil.getPage(q, page);
        putTours(request, page, search);
        request.setAttribute(RequestParam.URL, "search");
        request.setAttribute(RequestParam.PAGE, maxPage);
        return new Forward(URI.MAIN_JSP);
    }

    private void putTours(final HttpServletRequest request, int page, String search) {
        if (page == 0) {
            request.setAttribute(RequestParam.TOURS,
                    service.searchTourForRegion(search, 1));
        } else {
            request.setAttribute(RequestParam.TOURS,
                    service.searchTourForRegion(search, page));
        }
    }

}
