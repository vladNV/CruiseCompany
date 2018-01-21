package controller.cmd;

import controller.params.RequestParam;
import controller.util.RequestParser;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public Action defineAction(HttpServletRequest request) {
        Action current = new Empty();
        String action = RequestParser.getActionFromURI(request.getRequestURI());
        if (action == null || action.isEmpty()) return current;
        try {
            ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
            current = actionEnum.getAction();
        } catch (IllegalArgumentException e) {
            request.setAttribute(RequestParam.WRONG_ACTION, action);
        }
        return current;
    }
}
