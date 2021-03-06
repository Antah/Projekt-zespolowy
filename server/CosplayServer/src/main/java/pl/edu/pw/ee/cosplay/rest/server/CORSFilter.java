package pl.edu.pw.ee.cosplay.rest.server;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Ustawienia filtrów na połączenia. Raczej klasa.
 */
@Component
public class CORSFilter implements Filter {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Auth-Token, X-Requested-With, Content-Type, Accept,Subdomain");

        chain.doFilter(req, res);
    }

    public void init(final FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}