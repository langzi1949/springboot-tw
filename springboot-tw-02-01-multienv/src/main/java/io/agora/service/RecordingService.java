package io.agora.service;

import com.alibaba.fastjson.JSON;
import io.agora.media.AccessToken;
import io.agora.model.AgoraChannel;
import io.agora.recording.RecordingSDK;
import io.agora.recording.RecordingSample;
import io.agora.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/16 22:29
 **/
@Service
@Slf4j
public class RecordingService {

    /**
     *
     * @param channel
     */
    public String createRecording(AgoraChannel channel) throws Exception{
        log.info("参数为....{}", JSON.toJSONString(channel));
        RecordingSDK recordingSdk = new RecordingSDK();
        RecordingSample ars = new RecordingSample(recordingSdk);
        // 生成token
        String token = getToken(Consts.appId, Consts.certificate,"100001232_24235423","123456");
        String[] args = new String[]{"--appId", Consts.appId,"--channelKey",token,"--uid","123456","--channel","100001232_24235423","--appliteDir","/opt/agora/bin"};
        ars.createChannel(args);
        ars.unRegister();
        return null;
    }


    private String getToken(String appId,String certificate,String channelName,String uid) throws Exception {
        // 基于Agora 声网API生成token
        int expireSconds = (int)System.currentTimeMillis()/100 +60*60;

        AccessToken accessToken = new AccessToken(appId,certificate,channelName,uid);
        accessToken.addPrivilege(AccessToken.Privileges.kJoinChannel,expireSconds);
        return accessToken.build();
    }
}
