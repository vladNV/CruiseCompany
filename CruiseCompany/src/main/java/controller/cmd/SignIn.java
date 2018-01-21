package controller.cmd;

import static controller.util.MessageManager.getMessage;
import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.Cart;
import controller.util.URI;
import model.dao.FactoryDAO;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignIn implements Action {

    private UserService service;
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    SignIn() {
        this.service = new UserService(FactoryDAO.getDAOImpl(FactoryDAO.MYSQL));
    }

    //TODO add 500Exception
    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        if (login == null || password == null) {
            return new ActionResponse(Act.FORWARD, URI.LOGIN_JSP);
        }
        User user = service.signIn(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            // create cart for user
            Cart cart = new Cart();
            session.setAttribute("cart", cart);
            String redirectTo = (String) session.getAttribute("lastPath");
            System.out.println(redirectTo);
            if (redirectTo == null) return new ActionResponse(Act.REDIRECT, URI.MAIN);
            return new ActionResponse(Act.REDIRECT, redirectTo);

        } else {
            request.setAttribute("wrong", getMessage("auth_invalid"));
            return new ActionResponse(Act.FORWARD, URI.LOGIN_JSP);
        }

    }
}
