package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.TourBuilder;
import controller.util.URI;
import model.service.PortService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CruiseEditor implements Action {
    private PortService portService;

    CruiseEditor() {
        this.portService = new PortService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        request.setAttribute(RequestParam.PORTS, portService.selectPorts());
        TourBuilder tourBuilder = new TourBuilder();
        tourBuilder.setPorts(portService.selectPorts());
        request.getSession().setAttribute(SessionParam.BUILD_TOUR, tourBuilder);
        return new Forward(URI.ADD_CRUISE_JSP);
    }
}
