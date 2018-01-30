package controller.cmd.user;

import controller.cmd.Action;
import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.Regexp;
import controller.util.URI;
import controller.util.Verify;
import model.exceptions.UniqueException;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.util.RequestUtil.nullCheck;

public class SignUp implements Action {

    private UserService service;
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_REPASSWORD = "repassword";
    private static final String PARAM_LOGIN = "login";

    public SignUp() {
        service = new UserService();
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        Forward forward = new Forward(URI.SIGN_UP_JSP);
        String password = request.getParameter(PARAM_PASSWORD);
        String repassword = request.getParameter(PARAM_REPASSWORD);
        String login = request.getParameter(PARAM_LOGIN);
        String email = request.getParameter(PARAM_EMAIL);
        nullCheck(password, repassword, login, email);
        Verify verify = new Verify();
        if (!verify
                .regexp(Regexp.PASSWORD).incorrect("incorrect.password")
                .validate(password).validate(repassword)
                .regexp(Regexp.LOGIN).incorrect("incorrect.login").validate(login)
                .regexp(Regexp.EMAIL).incorrect("incorrect.email").validate(email)
                .allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }
        try {
            service.registration(password, login, email);
        } catch (UniqueException e) {
            request.setAttribute(RequestParam.WRONG,"email_not_unique");
            return forward;
        }
        return new Forward(URI.SUCCESS_REG_JSP);
    }

}
