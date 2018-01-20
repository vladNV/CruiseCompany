package controller;

import controller.cmd.Action;
import controller.cmd.ActionFactory;
import controller.util.ActionResponse;
import controller.util.MessageManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ActionFactory client = new ActionFactory();
        Action action = client.defineAction(req);
        ActionResponse actResp = action.execute(req, resp);
        if (actResp == null) {
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
                resp.sendError(400);
        }

    }

    private void redirectToPage(String page, HttpServletResponse resp)
            throws IOException {
        if (page != null) {
            resp.sendRedirect(page);
        } else {
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
            resp.sendError(404);
        }
    }

    @Override
    public String getServletName() {
        return super.getServletName();
    }
}
