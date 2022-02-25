package com.sheep.sheepoj.controller;


import com.sheep.sheepoj.entity.security.JwtUserDetails;
import com.sheep.sheepoj.service.UserService;
import com.sheep.sheepoj.utils.JsonResponseStatus;
import com.sheep.sheepoj.utils.JsonResult;
import io.jsonwebtoken.Jwt;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    JsonResult<String> register(HttpServletRequest request)
    {
        String userName=request.getParameter("userName");
        String rawPassword=request.getParameter("password");
        try {
            userService.register(userName,rawPassword);
        }
        catch (Exception e)
        {
            return new JsonResult<>(JsonResponseStatus.TOKEN_ERROR.getCode(), "注册失败："+e.getMessage());
        }
        return new JsonResult<>("注册成功");
    }

    @RequestMapping("/user")
    public JsonResult<Object> getCurrentUser()
    {
        return new JsonResult<>(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
