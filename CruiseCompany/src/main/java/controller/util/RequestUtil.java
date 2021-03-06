package controller.util;

import controller.exceptions.CommandException;
import model.service.TourService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtil {

    public static String getActionFromURI(String URI) {
        URI = URI.substring(1, URI.length());
        String[] s = URI.split("/");
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i].matches(Regexp.URI_ACTION)) {
                return s[i];
            }
        }
        throw new CommandException("null action");
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

    public static void nullArrayCheck(Object[] ... arrays) {
        for (Object[] array : arrays) {
            for (Object a : array) {
                if (a == null) {
                    throw new CommandException("null array");
                }
            }
        }
    }


    public static int getPage(int q, int page) {
        int maxPage = (q % TourService.LIMIT_TOUR == 0) ? q / TourService.LIMIT_TOUR :
                q / TourService.LIMIT_TOUR + 1;
        if (page > maxPage)
            throw new CommandException("so big page number");
        return maxPage;
    }

}
