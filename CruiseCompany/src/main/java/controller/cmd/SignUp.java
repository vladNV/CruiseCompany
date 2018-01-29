package controller.cmd;

import controller.params.RequestParam;
import controller.servlet.Forward;
import controller.servlet.ServletAction;
import controller.util.RegexpParam;
import controller.util.URI;
import futures.Param;
import futures.Verify;
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

        final Param password = new Param();
        password.setValue(request.getParameter(PARAM_PASSWORD));
        password.setIncorrect("incorrect.password");
        password.setRegexp(RegexpParam.PASSWORD);

        final Param repassword = new Param();
        repassword.setValue(request.getParameter(PARAM_REPASSWORD));
        repassword.setIncorrect("incorrect.password");
        repassword.setRegexp(RegexpParam.PASSWORD);

        final Param login = new Param();
        login.setValue(request.getParameter(PARAM_LOGIN));
        login.setIncorrect("incorrect.login");
        login.setRegexp(RegexpParam.LOGIN);

        final Param email = new Param();
        email.setValue(request.getParameter(PARAM_EMAIL));
        email.setIncorrect("incorrect.email");
        email.setRegexp(RegexpParam.EMAIL);

        Verify verify = new Verify();
        if (!verify.validate(email, password, repassword, login).allRight()) {
            request.setAttribute(RequestParam.WRONG, verify.getRemarks());
            return forward;
        }

        // TODO service exception
        if (!service.uniqueEmail(email.getValue())) {
            request.setAttribute(RequestParam.WRONG,"email_not_unique");
            return forward;
        }

        service.registration(password.getValue(), login.getValue(), email.getValue());
        return new Forward(URI.SUCCESS_REG_JSP);
    }

}
