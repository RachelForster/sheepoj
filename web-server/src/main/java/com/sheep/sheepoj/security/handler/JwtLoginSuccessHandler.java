package com.sheep.sheepoj.security.handler;
import com.alibaba.fastjson.JSON;
import com.sheep.sheepoj.entity.security.JwtUserDetails;
import com.sheep.sheepoj.utils.JsonResult;
import com.sheep.sheepoj.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //System.out.println(authentication.getPrincipal());
        //System.out.println(authentication.getCredentials());
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        String json = JSON.toJSONString(jwtUserDetails);
        String jwtToken = JwtUtils.createToken(json);
        //签发token
        JsonResult<String> jsonResult = new JsonResult<>(jwtToken);
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
