package by.epam.mtlcwtchr.ecafe.controller.localisationservice;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class LocalisationService {

    private LocalisationService(){

    }

    /**
     * localisation service with cookies reading/writing and locale session storing
     * @param request with session and current cookies access
     * @param response with cookies write-operation access
     */
    public static void setLocale(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
        final String locale = "locale";
        request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        if(Objects.nonNull(request.getParameter(locale))) {
            ((HttpServletRequest)request).getSession().removeAttribute(locale);
            ((HttpServletRequest)request).getSession().setAttribute(locale, request.getParameter(locale));
            final Cookie cookie = new Cookie(locale, request.getParameter(locale));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(1);
            ((HttpServletResponse) response).addCookie(cookie);
        }
    }
}
