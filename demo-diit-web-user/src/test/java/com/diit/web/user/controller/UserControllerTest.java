package com.diit.web.user.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 用户添加操作
     * @throws Exception
     */
    @Test
    public void insertUser() throws Exception {
        String json = "{\"account\":\"d\",\"password\":\"c4ca4238a0b923820dcc509a6f75849b\",\"name\":\"d\"}";
        mvc.perform(MockMvcRequestBuilders.post("/user/insertUser")
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .content(json.getBytes())
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户更新操作
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        String json = "{\"id\":\"6\",\"account\":\"c\",\"password\":\"c4ca4238a0b923820dcc509a6f75849b\",\"name\":\"cc\"}";
        mvc.perform(MockMvcRequestBuilders.post("/user/updateUser")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户删除操作
     * @throws Exception
     */
    @Test
    public void deleteUser() throws Exception {
        String json = "[9]";
        mvc.perform(MockMvcRequestBuilders.post("/user/deleteUser")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
}