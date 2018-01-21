package controller;

import controller.cmd.Action;
import controller.cmd.ActionFactory;
import controller.params.SessionParam;
import controller.util.ActionResponse;
import controller.util.MessageManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = {"/"})
public class Servlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("msg", MessageManager.getManager());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // ???
        HttpSession session = req.getSession();
        session.setAttribute(SessionParam.USER, req.getRequestURI());
        ActionFactory client = new ActionFactory();
        Action action = client.defineAction(req);
        ActionResponse actResp = action.execute(req, resp);
        if (actResp == null) {
            // Internal error
            resp.sendError(500);
            return;
        }
        switch (actResp.getAct()) {
            case FORWARD:
                forwardToPage(actResp.getPath(), req, resp);
                break;
            case INCLUDE:
                includePage(actResp.getPath(), req, resp);
                break;
            case REDIRECT:
                redirectToPage(actResp.getPath(), resp);
                break;
            default:
                // Bad request
                resp.sendError(400);
        }

    }

    private void redirectToPage(String page, HttpServletResponse resp)
            throws IOException {
        if (page != null) {
            resp.sendRedirect(page);
        } else {
            // Page not found
            resp.sendError(404);
        }
    }

    private void forwardToPage(String page,
                               HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            // Page not found
            resp.sendError(404);
        }
    }

    private void includePage(String page,
                             HttpServletRequest req,
                             HttpServletResponse resp)
            throws ServletException, IOException {
        if (page != null) {
            req.getRequestDispatcher(page).include(req, resp);
        } else {
            // Page not found
            resp.sendError(404);
        }
    }

}
