package controller.cmd;

import controller.util.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Back implements Action {
    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        return null;
    }
}