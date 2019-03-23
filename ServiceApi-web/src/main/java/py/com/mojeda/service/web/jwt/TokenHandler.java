package py.com.mojeda.service.web.jwt;


import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.spring.config.UserDetailService;
import py.com.mojeda.service.web.utils.Token;


public class TokenHandler {

    final long EXPIRATIONTIME = 50 * 60 * 1000; 		// 15 minutes
    final long UPDATE_EXPIRATIONTIME = 24 * 600 * 60 * 1000;
    final String SECRET = "Coomecipar";			// private key, better read it from an external file
    final public String UPDATE_SECRET = "UPDATE_*/*_TOKENS";
    final public String TOKEN_PREFIX = "X-Token";		// the prefix of the token in the http header
    final public String HEADER_STRING = "authorization";	// the http header containing the prexif + the token

    private final UserDetailsService userDetailsService = new UserDetailService();

    /**
     * Generate a token from the username.
     *
     * @param username	The subject from which generate the token.
     *
     * @return	The generated token.
     */
    public Token build(String username){
        Token token = new Token();       
        
        Date now = new Date();
        //TOKENS
        String JWT = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                //.compressWith(CompressionCodecs.DEFLATE) // uncomment to enable token compression
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
        //UPDATE TOKENS
        String UPDATE_JWT = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + UPDATE_EXPIRATIONTIME))
                //.compressWith(CompressionCodecs.DEFLATE) // uncomment to enable token compression
                .signWith(SignatureAlgorithm.HS512, UPDATE_SECRET)
                .compact();
        
        token.setExpires_in(EXPIRATIONTIME);
        token.setToken_type(TOKEN_PREFIX);
        token.setAccess_token(JWT);
        token.setRefresh_token(UPDATE_JWT);
    
        return token;

    }

    /**
     * Parse a token and extract the subject (username).
     *
     * @param token	A token to parse.
     *
     * @return	The subject (username) of the token.
     */
    public User parse(String token) {
                
        String idUser = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return (User) userDetailsService.loadUserByUsername(idUser);

    }
    
        

}
