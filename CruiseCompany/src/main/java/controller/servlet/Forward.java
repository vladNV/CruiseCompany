package controller.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Forward implements ServletAction {
    private final static Logger logger = Logger.getLogger(Forward.class);
    private String path;

    public Forward(String path) {
        this.path = path;
    }

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        logger.info("forward to " + path);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
