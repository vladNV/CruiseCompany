package controller.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect implements ServletAction {
    private String path;

    public Redirect(String path) {
        this.path = path;
    }

    @Override
    public void action(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(this.path);
    }
}
