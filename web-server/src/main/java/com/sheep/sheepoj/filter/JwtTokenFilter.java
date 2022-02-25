package com.sheep.sheepoj.filter;

import com.alibaba.fastjson.JSON;

import com.sheep.sheepoj.entity.security.JwtLoginToken;
import com.sheep.sheepoj.entity.security.JwtUserDetails;
import com.sheep.sheepoj.utils.JsonResponseStatus;
import com.sheep.sheepoj.utils.JsonResult;
import com.sheep.sheepoj.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = httpServletRequest.getHeader("Authentication");
            //如果没有token
            if (StringUtils.isEmpty(token)) {
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                JsonResult<String> jsonResult = new JsonResult<String>(JsonResponseStatus.TOKEN_ERROR.getCode(), "未登录");
                httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
                return;
            }
            //有token，就把它拿出来检验，检验失败就会报错
            Claims claims = JwtUtils.parseJWT(token);
            if (JwtUtils.isTokenExpired(claims)) {
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                JsonResult<String> jsonResult = new JsonResult<>(JsonResponseStatus.TOKEN_ERROR.getCode(), "登陆失效，请重新登陆");

                httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
                return;
            }
            //System.out.println(claims);
            JwtUserDetails user = JSON.parseObject(claims.get("jti", String.class), JwtUserDetails.class);
            JwtLoginToken jwtLoginToken = new JwtLoginToken(user.getUserName(), "", user.getAuthorities());
            jwtLoginToken.setDetails(new WebAuthenticationDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            JsonResult<String> jsonResult = new JsonResult<>(JsonResponseStatus.ACCESS_TOKEN_EXPIRED.getCode(), "登陆失效，请重新登陆");
            httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
            System.out.println(e.getMessage());
        }

    }

}
