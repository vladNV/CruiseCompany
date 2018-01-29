package controller.cmd;

import controller.exceptions.CommandException;
import controller.util.RequestUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private final static Logger logger = Logger.getLogger(ActionFactory.class);

    public Action defineAction(final HttpServletRequest request) {
        String action = RequestUtil.getActionFromURI(request.getRequestURI());
        try {
            ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
            logger.info("action");
            return actionEnum.getAction();
        } catch (IllegalArgumentException e) {
            logger.info("wrong action");
            logger.error(e);
            throw new CommandException("wrong action");
        }
    }
}
