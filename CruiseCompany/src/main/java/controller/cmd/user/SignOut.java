package controller.cmd.user;

import controller.cmd.Action;
import controller.params.SessionParam;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOut implements Action {

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionParam.USER);
        session.removeAttribute(SessionParam.BUILD_TOUR);
        session.removeAttribute(SessionParam.CART);
        session.removeAttribute(SessionParam.ROLE);
        session.removeAttribute(SessionParam.LASTPATH);
        return new Redirect(URI.MAIN);
    }
}
