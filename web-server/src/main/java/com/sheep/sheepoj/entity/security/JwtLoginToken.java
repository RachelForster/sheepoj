package com.sheep.sheepoj.entity.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtLoginToken extends AbstractAuthenticationToken {
    private Object userName;
    private Object password;

    public JwtLoginToken(Object userName,Object password) {
        super(null);
        this.password=password;
        this.userName=userName;
        setAuthenticated(false);

    }
    public JwtLoginToken(Object userName,Object password,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.password=password;
        this.userName=userName;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }
}
