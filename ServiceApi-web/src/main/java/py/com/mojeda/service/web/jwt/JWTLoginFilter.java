package py.com.mojeda.service.web.jwt;


import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.jwt.service.TokenAuthenticationService;
import py.com.mojeda.service.web.jwt.service.TokenAuthenticationServiceImpl;



public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private final TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
		// Retrieve username and password from the http request and save them in an Account object.
		User account = new ObjectMapper().readValue(req.getInputStream(), User.class);
		
		// Verify if the correctness of login details.
		// If correct, the successfulAuthentication() method is executed.
                //res.setHeader("Access-Control-Allow-Origin", "*");
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						account.getUsername(),
						account.getPassword(),
						Collections.EMPTY_LIST
						)
				);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		// Pass authenticated user data to the tokenAuthenticationService in order to add a JWT to the http response.
		tokenAuthenticationService.addAuthentication(res, auth);
	}

}
