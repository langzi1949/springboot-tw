package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
public class UserTest extends ApplicationTest {

    @Test
    public void teseUser() throws Exception {
        RequestBuilder requestBuilder = null;

        // 1.get 查一个 user列表信息
        requestBuilder = get("/users/");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));


        //2. post提交一个用户
        requestBuilder = post("/users/")
                .param("id","1")
                .param("name","langzi")
                .param("age","18");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("SUCCESS")));

        // 3. get获取列表
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"langzi\",\"age\":18}]")));

        // 4.put 修改 ID 为1 的 user

        requestBuilder = put("/users/1")
                .param("name","zmg")
                .param("age","20");

        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("SUCCESS")));

        // 5.看一下数据有没有更新完成
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"zmg\",\"age\":20}]")));

        // 6. del 删除用户
        requestBuilder = delete("/users/1");

        mockMvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("SUCCESS")));


        // 7.有没有删除
        requestBuilder = get("/users/");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
}
