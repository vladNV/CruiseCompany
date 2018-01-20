package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOut implements Action {

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ActionResponse(Act.REDIRECT, URI.MAIN);
    }
}
