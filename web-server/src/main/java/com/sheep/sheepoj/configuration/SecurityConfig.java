package com.sheep.sheepoj.configuration;

import com.sheep.sheepoj.filter.JwtLoginFilter;
import com.sheep.sheepoj.filter.JwtTokenFilter;
import com.sheep.sheepoj.security.JwtAuthenticationProvider;
import com.sheep.sheepoj.security.handler.JwtAuthenticationEntryPoint;
import com.sheep.sheepoj.security.handler.JwtLoginFailureHandler;
import com.sheep.sheepoj.security.handler.JwtLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    JwtLoginSuccessHandler loginSuccessHandler;
    @Autowired
    JwtLoginFailureHandler loginFailureHandler;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    JwtAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtLoginFilter loginFilter=new JwtLoginFilter();
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFilter.setAuthenticationManager(authenticationManagerBean());

        // ??????????????????????????????
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(userDetailsService, passwordEncoder());

        //??????token??????
        JwtTokenFilter tokenFilter=new JwtTokenFilter();
        // ??????????????????
        http.authenticationProvider(jwtAuthenticationProvider)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().cors();

        // jwt ???????????????
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //??????session
                .and()
                .csrf().disable()
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class) // ???????????????
                .addFilterAfter(tokenFilter,JwtLoginFilter.class);
        // ??????????????????
        http.exceptionHandling()
                //   ???????????????????????????????????????????????????????????????
                .accessDeniedHandler(accessDeniedHandler)
                // ?????????????????????????????????????????????????????????
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
