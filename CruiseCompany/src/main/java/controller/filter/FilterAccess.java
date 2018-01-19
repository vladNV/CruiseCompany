package controller.filter;

import controller.util.AccessConfig;
import model.util.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/"}, servletNames = "Servlet")
public class FilterAccess implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ifRoleNull(session);
        HashMap<Pattern,  Set<Role>> h = initAccessMap();
        String path = req.getRequestURI();
        Pattern pattern = isCorrectPath(h, path);
        if (pattern == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND ,path);
            return;
        }
        if (!redirect(req, pattern)) {
            resp.sendError(403);
            return;
        }
        chain.doFilter(request, response);
    }

    private HashMap<Pattern, Set<Role>> initAccessMap() {
        return AccessConfig.getAccessConfig().getAccess();
    }

    private void ifRoleNull(HttpSession session) {
        if (session.getAttribute("role") == null) {
            session.setAttribute("role", Role.GUEST);
        }
    }

    private boolean redirect(HttpServletRequest req, Pattern k) {
        HttpSession session = req.getSession();
        HashMap<Pattern, Set<Role>> h = initAccessMap();
        Set<Role> v = h.get(k);
        if (v == null) return false;
        Role current = (Role) session.getAttribute("role");
        return v.contains(current);
    }

    private Pattern isCorrectPath(HashMap<Pattern, Set<Role>> h, String path) {
        Matcher matcher;
        for (Pattern k : h.keySet()) {
            matcher = k.matcher(path);
            if(matcher.matches()) {
                return k;
            }
        }
        return null;
    }

    @Override
    public void destroy() { }


}
