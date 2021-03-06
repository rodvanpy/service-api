package py.com.mojeda.service.web.jwt;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import py.com.mojeda.service.web.jwt.service.TokenAuthenticationService;
import py.com.mojeda.service.web.jwt.service.TokenAuthenticationServiceImpl;



public class JWTAuthenticationFilter extends GenericFilterBean {
	
	private final TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		// Delegates authentication to the TokenAuthenticationService
		Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
		
		// Apply the authentication to the SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);               
		
                //tokenAuthenticationService.addUpdateTokensAuthentication((HttpServletRequest) request, (HttpServletResponse) response, authentication);
		// Go on processing the request
		filterChain.doFilter(request,response);

		// Clears the context from authentication
		SecurityContextHolder.getContext().setAuthentication(null);
		
	}

}
