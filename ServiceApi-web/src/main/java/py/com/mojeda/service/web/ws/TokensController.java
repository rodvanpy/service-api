/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.SignatureException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Tokens;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.web.jwt.TokenHandler;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.Token;
import py.com.mojeda.service.web.utils.TokensResponse;


/**
 *
 * @author miguel.ojeda
 */
@Controller
public class TokensController extends BaseController {
    
    private final TokenHandler tokenHandler = new TokenHandler();  
    /**
     * Mapping para el metodo GET del  Token.
     *
     * @param request
     * @param res
     * @return
     */
    @GetMapping("/tokens_user")
    public @ResponseBody
    ResponseDTO getUserInfo(HttpServletRequest request, HttpServletResponse res) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        TokensResponse tokensInfo = new TokensResponse();
        try {            

            Token JWT = tokenHandler.build(userDetail.getId().toString());
            
            tokensInfo.setToken(JWT);
            tokensInfo.setUsuario(userDetail);
            
            response.setModel(tokensInfo);
            response.setStatus(200);
            response.setMessage("OK");

        } catch (Exception e) {
            res.setStatus(401);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
}
