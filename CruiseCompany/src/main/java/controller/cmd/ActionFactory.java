package controller.cmd;

import controller.params.RequestParam;
import controller.servlet.ServletAction;
import controller.util.RequestParser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private final static Logger logger = Logger.getLogger(ActionFactory.class);

    public Action defineAction(HttpServletRequest request) {
        String action = RequestParser.getActionFromURI(request.getRequestURI());
        if (action == null || action.isEmpty())
            logger.info("action is null");
            ServletAction.error("action is null");
        try { ;
            ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
            logger.info("action");
            return actionEnum.getAction();
        } catch (IllegalArgumentException e) {
            logger.info("wrong action");
            logger.error(e);
            request.setAttribute(RequestParam.WRONG_ACTION, action);
            return null;
        }
    }
}
