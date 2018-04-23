package com.diit.core.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.diit.core.user.pojo.User;

public interface UserMapper {

    List<User> listAll();
    
    User getUserById(@Param("id") Long id);
    
    User getUserByAccount(@Param("account") String account);
    
    User getUserByNameAndPwd(@Param("account") String account, @Param("password") String password);
    
    int insertUser(@Param("user") User user);
    
    int updateUser(@Param("user") User user);
    
    int deleteBatch(Long[] ids);
}

