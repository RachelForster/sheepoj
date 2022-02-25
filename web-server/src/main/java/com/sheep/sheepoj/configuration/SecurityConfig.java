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

        // 使用自定义验证实现器
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(userDetailsService, passwordEncoder());

        //验证token信息
        JwtTokenFilter tokenFilter=new JwtTokenFilter();
        // 登陆验证信息
        http.authenticationProvider(jwtAuthenticationProvider)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().cors();

        // jwt 拦截器配置
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //禁用session
                .and()
                .csrf().disable()
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class) // 添加拦截器
                .addFilterAfter(tokenFilter,JwtLoginFilter.class);
        // 权限处理信息
        http.exceptionHandling()
                //   用来解决认证过的用户访问无权限资源时的异常
                .accessDeniedHandler(accessDeniedHandler)
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
