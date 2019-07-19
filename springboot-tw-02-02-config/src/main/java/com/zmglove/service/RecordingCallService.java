package com.zmglove.service;

import com.zmglove.agora.RecordingSample;
import com.zmglove.model.AgoraChannel;
import com.zmglove.util.Consts;
import io.agora.recording.RecordingSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/17 17:50
 **/
@Service
@Slf4j
public class RecordingCallService {

    @Async
    public void callAgora(AgoraChannel channel, RecordingSample recordingSample){
//        log.info(">>>>>>>>>>>>进入录制..................................\n");

        RecordingSDK recordingSdk = new RecordingSDK();
        recordingSample.setRecordingSDKInstance(recordingSdk);
        String[] args = new String[]{"--appId", Consts.appId,"--channelKey",channel.getToken(),"--uid","0","--channel",channel.getName(),"--appliteDir","/opt/agora/bin",
                "--hfjyChannel",String.valueOf(channel.getId()),
                "--isMixingEnabled","1",
                "--mixResolution","1280,720,15,600",
                "--mixedVideoAudio","2",
                "--idle","3",
                "--triggerMode","0",
                "--lowUdpPort","40000",
                "--highUdpPort","40100",
                };
        recordingSample.createChannel(args);
        recordingSample.unRegister();
    }
}
