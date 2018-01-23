package controller.cmd;

import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements Action {

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        request.getSession().invalidate();
        return new Redirect(URI.MAIN);
    }
}
