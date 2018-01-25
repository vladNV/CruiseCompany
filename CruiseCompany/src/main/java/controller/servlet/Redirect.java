package controller.servlet;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect implements ServletAction {
    private final static Logger logger = Logger.getLogger(Redirect.class);
    private String path;

    public Redirect(String path) {
        this.path = path;
    }

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("redirect to " + path);
        resp.sendRedirect(this.path);
    }
}
