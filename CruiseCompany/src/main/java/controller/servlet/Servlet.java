package controller.servlet;

import controller.cmd.Action;
import controller.cmd.ActionFactory;
import controller.exceptions.CommandException;
import controller.params.SessionParam;
import controller.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = {"/"})
public class Servlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(Servlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("msg", MessageManager.getManager());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info(req.getLocalAddr());
        logger.info(getServletName() + " get request");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info(req.getLocalAddr());
        logger.info(getServletName() + " post request");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            logger.info(req.getRequestURL().toString());
            ActionFactory client = new ActionFactory();
            Action action = client.defineAction(req);
            ServletAction dispatcher = action.execute(req, resp);
            dispatcher.action(req, resp);
            session.setAttribute(SessionParam.PATH, req.getRequestURL().toString());
        } catch (CommandException e) {
            e.printStackTrace();
            logger.error("command exception", e);
            resp.sendError(400, e.getMessage());
        } catch (Exception e) {
            logger.error("internal server error", e);
            e.printStackTrace();
            resp.sendError(500);
        }
    }

}
