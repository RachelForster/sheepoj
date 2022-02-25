package com.sheep.sheepoj.security.handler;

import com.alibaba.fastjson.JSON;
import com.sheep.sheepoj.utils.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        JsonResult<String> jsonResult=new JsonResult<>(500,"权限不足:"+authException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
