package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/"}, servletNames = "Servlet")
public class FilterLocale implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        // default locale
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", Locale.ENGLISH);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // nothing
    }
}
