package py.com.mojeda.service.web.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 5907023648091540313L;

	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        final String expired = (String) request.getAttribute("expired");
        System.out.println(expired);
        if (expired!=null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario o Contraseña Inválidos");
        }
    }
}