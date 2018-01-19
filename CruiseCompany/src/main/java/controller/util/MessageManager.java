package controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static MessageManager manager;

    private MessageManager() {
    }

    public static MessageManager getManager() {
        if (manager == null) {
            synchronized (AccessConfig.class) {
                if (manager == null) {
                    manager = new MessageManager();
                }
            }
        }
        return manager;
    }

    private static ResourceBundle resource = ResourceBundle.getBundle("messages", Locale.ENGLISH);
    private static Locale UKRAINE = new Locale("ua","UA");

    public static String getMessage(String key) {
        return resource.getString(key);
    }

    public static void switchLanguage(Locale locale) {
        resource = ResourceBundle.getBundle("messages", locale);
    }

}
