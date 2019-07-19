package com.zmglove.service;

import com.alibaba.fastjson.JSON;
import com.zmglove.agora.RecordingSample;
import com.zmglove.model.AgoraChannel;
import com.zmglove.util.Consts;
import io.agora.media.AccessToken;
import io.agora.recording.RecordingSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/17 9:53
 **/
@Service
@Slf4j
public class RecordingService {
//    @Autowired
//    private RecordingSample recordingSample;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private RecordingCallService recordingCallService;

    /**
     *
     * @param channel
     */
    public String createRecording(AgoraChannel channel) throws Exception{
        log.info("参数为....{}", JSON.toJSONString(channel));
        // 这个必须要是多例 因为需要观察回调
        RecordingSample recordingSample = beanFactory.getBean(RecordingSample.class);
        //log.info(">>>>>recordingSample的地址为{},string = {}",recordingSample.hashCode(),recordingSample.toString());

        // 生成token
        //String token = getToken(Consts.appId, Consts.certificate,channel.getName(),String.valueOf(channel.getTeacherId()));
        recordingCallService.callAgora(channel,recordingSample);
        return JSON.toJSONString(channel);
    }

    private String getToken(String appId,String certificate,String channelName,String uid) throws Exception {
        // 基于Agora 声网API生成token
        int expireSconds = (int)System.currentTimeMillis()/100 +60*60;

        AccessToken accessToken = new AccessToken(appId,certificate,channelName,uid);
        accessToken.addPrivilege(AccessToken.Privileges.kJoinChannel,expireSconds);
        return accessToken.build();
    }
}
