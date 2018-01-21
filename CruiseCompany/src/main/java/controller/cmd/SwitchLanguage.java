package controller.cmd;

import controller.util.Act;
import controller.util.ActionResponse;
import controller.util.MessageManager;
import controller.util.URI;
import sun.plugin2.message.Message;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
        MessageManager.switchLanguage(locale);
        System.out.println(MessageManager.getMessage("language"));
        return new ActionResponse(Act.REDIRECT, URI.MAIN);
    }
}
