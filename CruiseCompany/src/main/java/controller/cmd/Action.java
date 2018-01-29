package controller.cmd;

import controller.servlet.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    ServletAction execute(final HttpServletRequest request,
                          final HttpServletResponse response);

}
