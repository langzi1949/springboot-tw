package com.zmglove.web;

import cn.hutool.core.io.FileUtil;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.zmglove.Application;
import com.zmglove.service.MergeMp4Service;
import com.zmglove.service.Mp4Service;
import com.zmglove.util.UFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
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

//    @Autowired
//    private StudentApiConfig studentApiConfig;

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

//    @Test
//    public void studentApiConfigTest(){
////        Assert.assertEquals("zmglove-学生的服务接口",studentApiConfig.getDesc());
////
////        log.info("获取的随机字符串为: {}",studentApiConfig.getValue());
//    }

//    @Test
//    public void mergeVideo(){
//        List<String> urls  = new ArrayList<>();
//
//        urls.add("/Users/langzi/Desktop/test/10001.mp4");
//
//
//        try {
//            mergeMp4Service.videoMerge(urls,"/Users/langzi/Desktop/test/merge");
//            // 删除文件
//            FileUtil.del(urls.get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void getVideoTime() throws EncoderException {
//        mp4Service.getTime("G:\\merge\\mergeOutput2.mp4");
//    }

//    @Test
//    public void ufileTest() throws UfileServerException, UfileClientException {
//        UFileUtil fileUtil = new UFileUtil("TOKEN_b5daa53f-0e97-49df-a88e-109cdba92545",
//                "6567ebc3-8218-42b1-9f4a-12bc0084a1a1");
//        fileUtil.upload("/Users/langzi/Desktop/10001.mp4");
//
//    }


    @Test
    public void uFileMultiUploadTest() throws UfileServerException, UfileClientException {
        UFileUtil fileUtil = new UFileUtil("TOKEN_b5daa53f-0e97-49df-a88e-109cdba92545",
                "6567ebc3-8218-42b1-9f4a-12bc0084a1a1");
        fileUtil.multiUpload("G:\\videos\\33.mp4");
    }

}
