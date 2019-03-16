package py.com.mojeda.service.web.tokens.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.tokens.TokenHandler;
import py.com.mojeda.service.web.utils.LoginResponse;
import py.com.mojeda.service.web.utils.Token;



@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {
    
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(TokenAuthenticationServiceImpl.class);

    private final TokenHandler tokenHandler = new TokenHandler();
    Gson gson = new GsonBuilder().create();

    /**
     * When a user successfully logs into the application, create a token for
     * that user.
     *
     * @param res	An http response that will be filled with an 'Authentication'
     * header containing the token.
     * @param authentication
     */
    @Override
    public void addAuthentication(HttpServletResponse res, Authentication authentication) {
        LoginResponse response = new LoginResponse();
        try {
            
            User userDetail = ((User) authentication.getPrincipal());
            userDetail.setPassword("xxxxxxx");
            
            Token JWT = tokenHandler.build(userDetail.getId()+"");
            
            response.setToken(JWT);
            response.setUsuario(userDetail);

            res.addHeader(tokenHandler.HEADER_STRING, tokenHandler.TOKEN_PREFIX + " " + JWT.getAccess_token());
            res.setHeader("Content-Type", "application/json;charset=UTF-8");
            PrintWriter writer = res.getWriter();           
            writer.write(gson.toJson(response));
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * The JWTAuthenticationFilter calls this method to verify the user
     * authentication. If the token is not valid, the authentication fails and
     * the request will be refused.
     *
     * @param request	An http request that will be check for authentication
     * token to verify.
     * @return
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        List<GrantedAuthority> autoridades = new ArrayList<>();       
        String token = request.getHeader(tokenHandler.HEADER_STRING);
        logger.info("TOKENS: "+ token);
        if (token != null && token.startsWith(tokenHandler.TOKEN_PREFIX)) {
            // Parse the token.
            User user = null;
            try {
                
                user = tokenHandler.parse(token);
                
                logger.info("getUsername: "+ user.getUsername());
                logger.info("getId: "+ user.getId());            
                
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            } else {
                return null;
            }

        }

        return null;

    }   

}
