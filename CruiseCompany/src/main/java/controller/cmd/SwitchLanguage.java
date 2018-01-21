package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SwitchLanguage implements Action {
    private Locale locale;

    SwitchLanguage(Locale locale) {
        this.locale = locale;
    }

    @Override
    public ActionResponse execute(HttpServletRequest request,
                                  HttpServletResponse response) {
        request.getServletContext().setAttribute("locale", locale);
        return new ActionResponse(Act.REDIRECT, URI.MAIN);
    }
}
