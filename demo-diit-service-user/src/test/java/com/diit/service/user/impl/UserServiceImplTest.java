package com.diit.service.user.impl;

import com.diit.core.user.mapper.UserMapper;
import com.diit.core.user.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    /**
    * 获取单个用户
    */
    @Test
    public void getUser() {
        User user = userMapper.getUserById(1L);
        Assert.assertThat(user.getAccount(), is("admin"));
    }

    /**
     * 获取单个用户
     */
    @Test
    public void getUserByAccount() {
        User user = userMapper.getUserByAccount("a");
        Assert.assertThat(user.getName(), is("a"));
    }

    /**
     * 新增/更新用户
     */
    @Test
    @Transactional
    public void saveUser() {
        User user = new User();
        user.setAccount("d");
        user.setPassword(DigestUtils.md5DigestAsHex("1".getBytes()));
        user.setName("c");
        userMapper.insertUser(user);
    }

    /**
     * 删除用户
     */
    @Test
    @Transactional
    public void deleteUser() {
        Long[] ids = {6L};
        userMapper.deleteBatch(ids);
    }
}