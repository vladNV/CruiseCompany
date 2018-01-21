package controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static MessageManager manager;
    private static ResourceBundle resource = ResourceBundle.getBundle("messages", Locale.ENGLISH);
    public static Locale UKRAINE = new Locale("uk","UA");

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

    public static String getMessage(String key) {
        return resource.getString(key);
    }

}
