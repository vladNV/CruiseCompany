package controller.cmd;

import controller.servlet.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action interface
 */
public interface Action {

    /**
     *
     * @param request is http request
     * @param response is http response
     * @return action which control page
     */
    ServletAction execute(final HttpServletRequest request,
                          final HttpServletResponse response);

}
