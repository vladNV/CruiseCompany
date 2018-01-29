package controller.cmd;

import controller.params.RequestParam;
import controller.params.SessionParam;
import controller.servlet.Forward;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.*;
import futures.Verify;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.util.RequestUtil.nullCheck;

public class SignIn implements Action {

    private UserService service;
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    SignIn() {
        this.service = new UserService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        Forward forward = new Forward(URI.LOGIN_JSP);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        nullCheck(email, password);
        Verify verify = new Verify();
        if (!verify.incorrect("auth_invalid")
                .regexp(Regexp.EMAIL).validate(email)
                .regexp(Regexp.PASSWORD).validate(password)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }
        User user = service.signIn(email, password);
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
