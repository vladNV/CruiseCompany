package controller.cmd.user;

import controller.cmd.Action;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Action {
    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        return new Forward(URI.LOGIN_JSP);
    }
}
