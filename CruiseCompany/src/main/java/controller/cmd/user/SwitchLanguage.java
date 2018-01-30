package controller.cmd.user;

import controller.cmd.Action;
import controller.util.Currency;
import controller.params.SessionParam;
import controller.servlet.Redirect;
import controller.servlet.ServletAction;
import controller.util.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SwitchLanguage implements Action {
    private Locale locale;

    public SwitchLanguage(Locale locale) {
        this.locale = locale;
    }

    @Override
    public ServletAction execute(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionParam.LOCALE, locale);
        session.setAttribute(SessionParam.CURRENCY, Currency.currency(locale).value());
        return new Redirect(URI.MAIN);
    }
}