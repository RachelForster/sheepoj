package com.sheep.sheepoj.filter;

import com.sheep.sheepoj.entity.security.JwtLoginToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        //System.out.println(userName);
        //System.out.println(password);
        JwtLoginToken token=new JwtLoginToken(userName,password);
        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication=this.getAuthenticationManager().authenticate(token);
        return authentication;
    }
}
