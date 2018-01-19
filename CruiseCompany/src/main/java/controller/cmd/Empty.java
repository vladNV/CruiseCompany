package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Empty implements Action {
    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        return new ActionResponse(Act.NONE, "");
    }
}
