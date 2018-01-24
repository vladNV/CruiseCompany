package controller.util;

import controller.exceptions.CommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    public static String getActionFromURI(String URI) {
        URI = URI.substring(1, URI.length());
        String[] s = URI.split("/");
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i].matches(RegexpURI.URI_ACTION)) {
                return s[i];
            }
        }
        return null;
    }

    public static int getIdFromURI(String URI) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(URI);
        int id;
        String m = "";
        while (matcher.find()) {
            m = matcher.group();
        }
        try {
            id = Integer.parseInt(m);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return id;
    }

     public static String getTicketType(String uri) {
        String ticketType = "";
        Pattern pattern = Pattern.compile(RegexpURI.TICKET_TYPE);
        Matcher matcher = pattern.matcher(uri);
        while (matcher.find()) {
            ticketType = matcher.group();
        }
        return ticketType;
    }

    public static boolean validate(String param, String regex) {
        return param.matches(regex);
    }

    public static boolean validate(String params[], String regex) {
        for (String param: params) {
            if (!param.matches(regex)) {
                return false;
            }
        }
        return true;
    }

    public static void nullCheck(Object ... params) {
        for (Object o : params) {
            if (o == null) {
                throw new CommandException("null params");
            }
        }
    }

    public static boolean isNull(Object ... params) {
        for (Object o : params) {
            if (o == null) return true;
        }
        return false;
    }

}
