package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Forward implements ServletAction {
    private String path;

    public Forward(String path) {
        this.path = path;
    }

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
