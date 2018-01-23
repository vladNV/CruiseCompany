package controller.cmd;

import controller.params.RequestParam;
import controller.servlet.ServletAction;
import controller.util.RequestParser;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public Action defineAction(HttpServletRequest request) {
        String action = RequestParser.getActionFromURI(request.getRequestURI());
        if (action == null || action.isEmpty())
            ServletAction.error("action is null");
        try {
            ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
            return actionEnum.getAction();
        } catch (IllegalArgumentException e) {
            request.setAttribute(RequestParam.WRONG_ACTION, action);
            return null;
        }
    }
}
