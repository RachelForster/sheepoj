package com.sheep.sheepoj.security.handler;

import com.alibaba.fastjson.JSON;
import com.sheep.sheepoj.utils.JsonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        JsonResult<String> jsonResult=new JsonResult<>(500,"获取失败:"+accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
