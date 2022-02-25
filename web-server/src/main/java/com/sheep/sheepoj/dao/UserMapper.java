package com.sheep.sheepoj.dao;

import com.sheep.sheepoj.entity.security.JwtUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserMapper {

    JwtUserDetails findByUserName(String userName);
    void putOne(@Param("userName") String userName, @Param("password") String password);
}
