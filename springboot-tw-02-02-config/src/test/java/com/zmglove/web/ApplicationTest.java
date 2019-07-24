package com.zmglove.web;

import com.zmglove.Application;
import com.zmglove.config.StudentApiConfig;
import com.zmglove.service.MergeMp4Service;
import com.zmglove.service.Mp4Service;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ws.schild.jave.EncoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 关于Application的测试类
 *
 * @author CZH,
 * @version 1.0
 * @date 2019/4/22 11:04
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class, Application.class})
@WebAppConfiguration
@Slf4j
public class ApplicationTest {
    private MockMvc mockMvc;

    @Autowired
    private StudentApiConfig studentApiConfig;

    @Autowired
    private MergeMp4Service mergeMp4Service;

    @Autowired
    private Mp4Service mp4Service;


    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void helloTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Hello World")));
    }

    @Test
    public void studentApiConfigTest(){
        Assert.assertEquals("zmglove-学生的服务接口",studentApiConfig.getDesc());

        log.info("获取的随机字符串为: {}",studentApiConfig.getValue());
    }

    @Test
    public void mergeVideo(){
        List<String>  urls  = new ArrayList<>();

        for(int i=1;i<=16;i++){
            urls.add("G:\\vido\\a"+i+".mp4");
        }


        try {
            mergeMp4Service.videoMerge(urls,"G:\\merge\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void getVideoTime() throws EncoderException {
//        mp4Service.getTime("G:\\merge\\mergeOutput2.mp4");
//    }

}
