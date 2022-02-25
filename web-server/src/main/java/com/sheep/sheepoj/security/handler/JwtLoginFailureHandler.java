package com.sheep.sheepoj.security.handler;

import com.alibaba.fastjson.JSON;
import com.sheep.sheepoj.entity.security.JwtUserDetails;
import com.sheep.sheepoj.utils.JsonResult;
import com.sheep.sheepoj.utils.JwtUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        JsonResult<String> jsonResult = new JsonResult<>(500,"登录失败");
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
