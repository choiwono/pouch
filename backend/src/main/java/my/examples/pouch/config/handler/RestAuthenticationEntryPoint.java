package my.examples.pouch.config.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class RestAuthenticationEntryPoint /*implements AuthenticationEntryPoint*/ {
    /*@Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "ServerError");
    }*/
}
