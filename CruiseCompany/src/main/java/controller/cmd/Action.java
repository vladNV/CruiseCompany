package controller.cmd;

import controller.servlet.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    ServletAction execute(HttpServletRequest request,
                          HttpServletResponse response);

}
