package controller.servlet;

import controller.cmd.Action;
import controller.cmd.ActionFactory;
import controller.exceptions.CommandException;
import controller.params.SessionParam;
import controller.util.MessageManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = {"/"})
public class Servlet extends HttpServlet {
    public static final String GET = "GET";
    public static final String POST = "POST";


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
        try {
            HttpSession session = req.getSession();
            session.setAttribute(SessionParam.PATH, req.getRequestURI());
            ActionFactory client = new ActionFactory();
            Action action = client.defineAction(req);
            ServletAction dispatcher = action.execute(req, resp);
            dispatcher.action(req, resp);
        } catch (CommandException e) {
            e.printStackTrace();
            resp.sendError(400, e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }

}
