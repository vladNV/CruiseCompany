package controller.cmd;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ActionFactory {
    public Action defineAction(HttpServletRequest request) {
        Action current = new Empty();
        String action = parseAction(request.getRequestURI());
        if (action == null || action.isEmpty()) return current;
        try {
            ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
            current = actionEnum.getAction();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
        }
        return current;
    }

    // TODO fix me pls
    private String parseAction(String URI)  {
        URI = URI.replaceAll("/", "");
        URI = URI.replaceAll("\\d","");
        return URI;
    }
}
