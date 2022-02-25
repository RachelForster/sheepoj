package com.sheep.sheepoj.service.impl;

import com.sheep.sheepoj.dao.UserMapper;
import com.sheep.sheepoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void register(String userName,String rawPassword) throws Exception
    {
        String password=encoder.encode(rawPassword);
        System.out.println(password);
        userMapper.putOne(userName,password);
    }
}
