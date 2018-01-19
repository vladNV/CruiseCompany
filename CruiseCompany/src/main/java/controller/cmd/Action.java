package controller.cmd;

import controller.util.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    ActionResponse execute(HttpServletRequest request,
                           HttpServletResponse response);

}
