package com.sheep.sheepoj.service;

import com.sheep.sheepoj.dao.UserMapper;
import com.sheep.sheepoj.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
//    @Autowired
//    UserMapper userMapper;
    void register(String userName,String rawPassword) throws Exception;

}
