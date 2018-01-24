package controller.cmd;

import static controller.util.RequestParser.isNull;
import static controller.util.RequestParser.validate;

import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.URI;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Action {

    private UserService service;
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_REPASSWORD = "repassword";
    private static final String PARAM_LOGIN = "login";

    SignUp() {
        service = new UserService();
    }

    @Override
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        Forward forward = new Forward(URI.SIGN_UP_JSP);
        String password = request.getParameter(PARAM_PASSWORD);
        String repassword = request.getParameter(PARAM_REPASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String login = request.getParameter(PARAM_LOGIN);
        if (isNull(password, repassword, email, login)) {
            return forward;
        }
        if (!validateParam(request, login, password, repassword, email)) {
            return forward;
        }
        if (!password.equals(repassword)) {
            request.setAttribute(RequestParam.WRONG, "password_notequals");
            return forward;
        }
        // TODO ONE QUERY
        if (!service.uniqueEmail(email)) {
            request.setAttribute(RequestParam.WRONG,"email_not_unique");
            return forward;
        }
        service.registration(password, login, email);
        return new Forward(URI.SUCCESS_REG_JSP);
    }

    private static boolean validateParam(HttpServletRequest request,
                                        String login, String password,
                                        String repassword, String email) {
        boolean isValid = true;
        if (!validate(email, RegexpParam.EMAIL)) {
            request.setAttribute("wrongEmail","incorrect.email");
            isValid = false;
        }
        if (!validate(login, RegexpParam.LOGIN)) {
            request.setAttribute("wrongLogin","incorrect.login");
            isValid = false;
        }
        if (!validate(password, RegexpParam.PASSWORD)) {
            request.setAttribute("wrongPass","incorrect.password");
            isValid = false;
        }
        if (!validate(repassword, RegexpParam.PASSWORD)) {
            request.setAttribute("wrongPass","incorrect.password");
            isValid = false;
        }
        return isValid;
    }

}
