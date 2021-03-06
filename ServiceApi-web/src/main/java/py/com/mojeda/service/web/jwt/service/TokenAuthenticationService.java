package py.com.mojeda.service.web.jwt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;


public interface TokenAuthenticationService {

	/**
	 * When a user successfully logs into the application, create a token for that user.
	 * 
	 * @param res		An http response that will be filled with an 'Authentication' header containing the token.
         * @param authentication
	 */
	void addAuthentication(HttpServletResponse res, Authentication authentication);
        
        void addUpdateTokensAuthentication(HttpServletRequest request, HttpServletResponse res, Authentication authentication);

	/**
	 * The JWTAuthenticationFilter calls this method to verify the user authentication.
	 * If the token is not valid, the authentication fails and the request will be refused.
	 * 
	 * @param request	An http request that will be check for authentication token to verify.
	 * @return
	 */
	Authentication getAuthentication(HttpServletRequest request, HttpServletResponse res);

}