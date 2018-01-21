package controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void setCookie(Cookie cookie, HttpServletResponse r) {
        r.addCookie(cookie);
    }

    public static void setCookie(Cookie cookie, HttpServletResponse r, int time) {
        cookie.setMaxAge(time);
        r.addCookie(cookie);
    }

    // TODO delete
    public static void printCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }
    }

}
