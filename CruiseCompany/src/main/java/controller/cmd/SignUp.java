package controller.cmd;

import static controller.util.MessageManager.getMessage;

import controller.params.RequestParam;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;
import model.dao.FactoryDAO;
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
        service = new UserService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        ActionResponse actResp = new ActionResponse(Act.FORWARD, URI.SIGN_UP_JSP);
        String password = request.getParameter(PARAM_PASSWORD);
        String repassword = request.getParameter(PARAM_REPASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String login = request.getParameter(PARAM_LOGIN);
        if(password == null || repassword == null
                || email == null || login == null) {
            return actResp;
        }
        if (!service.uniqueEmail(email)) {
            request.setAttribute(RequestParam.WRONG, getMessage("email_not_unique"));
            return actResp;
        }
        if (!password.equals(repassword)) {
            request.setAttribute(RequestParam.WRONG, getMessage("password_notequals"));
            return actResp;
        }
        service.registration(password, login, email);
        return new ActionResponse(Act.FORWARD, URI.SUCCESS_REG_JSP);
    }

}
