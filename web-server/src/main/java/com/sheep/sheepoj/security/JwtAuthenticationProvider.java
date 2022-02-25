package com.sheep.sheepoj.security;

import com.sheep.sheepoj.entity.User;
import com.sheep.sheepoj.entity.security.JwtLoginToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;
    private PasswordEncoder encoder;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    //完成从未认证到认证的过程
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails=userDetailsService.loadUserByUsername(authentication.getName());
        check((JwtLoginToken) authentication,userDetails);
        Authentication authToken=new JwtLoginToken(userDetails,authentication.getCredentials(),userDetails.getAuthorities());
        return authToken;
    }

    //验证密码是否正确
    private void check(JwtLoginToken token,UserDetails userDetails) throws BadCredentialsException {
        if (!encoder.matches(token.getCredentials().toString(), userDetails.getPassword()))
        {
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtLoginToken.class.isAssignableFrom(authentication));
    }
}
