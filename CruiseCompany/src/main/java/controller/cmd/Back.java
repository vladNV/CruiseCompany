package controller.cmd;

import controller.servlet.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Back implements Action {
    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        return null;
    }
}
