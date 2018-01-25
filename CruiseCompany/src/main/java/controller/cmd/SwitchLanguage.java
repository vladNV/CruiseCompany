package controller.cmd;

import controller.params.ApplicationParam;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
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
    public ServletAction execute(HttpServletRequest request,
                                 HttpServletResponse response) {
        request.getSession().setAttribute(ApplicationParam.LOCALE, locale);
        return new Redirect(URI.MAIN);
    }
}