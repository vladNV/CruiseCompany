package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.*;
import futures.Param;
import futures.Verify;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn implements Action {

    private UserService service;
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    SignIn() {
        this.service = new UserService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession();
        Forward forward = new Forward(URI.LOGIN_JSP);

        final  Param email = new Param();
        email.setValue(request.getParameter(PARAM_EMAIL));
        email.setIncorrect("auth_invalid");
        email.setRegexp(RegexpParam.EMAIL);

        final Param password = new Param();
        password.setValue(request.getParameter(PARAM_PASSWORD));
        password.setIncorrect("auth_invalid");
        password.setRegexp(RegexpParam.PASSWORD);

        Verify verify = new Verify();
        if (!verify.validate(email, password).allRight()) {
            request.setAttribute(RequestParam.WRONG, "auth_invalid");
            return forward;
        }
        User user = service.signIn(email.getValue(), password.getValue());
        if (user != null) {
            session.setAttribute(SessionParam.USER, user);
            session.setAttribute(SessionParam.ROLE, user.getRole());
            session.setAttribute(SessionParam.CART, new Cart());
            String redirectTo = (String) session.getAttribute(SessionParam.LASTPATH);
            if (redirectTo == null) return new Redirect(URI.MAIN);
            return new Redirect(redirectTo);
        } else {
            request.setAttribute(RequestParam.WRONG, "auth_invalid");
            return forward;
        }
    }
}
