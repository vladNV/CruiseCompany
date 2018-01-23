package controller.servlet;

import controller.exceptions.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServletAction {

    void action(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException;

    static void error(String cause) {
        throw new CommandException(cause);
    }
}
