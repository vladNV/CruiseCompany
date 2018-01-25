package controller.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Include implements ServletAction {
    private final static Logger logger = Logger.getLogger(Include.class);
    private String path;

    public Include(String path) {
        this.path = path;
    }

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        logger.info("include " + path);
        req.getRequestDispatcher(path).include(req, resp);
    }
}
