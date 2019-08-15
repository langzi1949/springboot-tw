package com.zmglove.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/17 11:39
 **/
@Service
@Slf4j
public class ZmgloveService {

    public void sayHello() {
        log.info("########SAYHELLO#########");
    }

    public void onLeaveChannel(Integer channelId, Long recordingId, String recordingPath, Integer reasonCode) {
        log.info(">>>>回调onLeaveChannel,其中参数channelId:{},recordingId:{},recordingPath:{},reasonCode:{}",
                channelId, recordingId, recordingPath, reasonCode);
    }

    public void onError(Integer channelId, Long recordingId, Integer errorCode, Integer stateCode) {
        log.info(">>>>回调onError,其中参数channelId:{},recordingId:{},errorCode:{},stateCode:{}",
                channelId, recordingId, errorCode, stateCode);
    }


    public void onWarning(Integer channelId, Long recordingId, Integer warn) {
        log.info(">>>>回调onWarning,其中参数channelId:{},recordingId:{},warn:{}",
                channelId, recordingId, warn);
    }

    public void onJoinChannelSuccess(Integer channelId, String channelName, Long recordingId) {
        log.info(">>>>回调onJoinChannelSuccess,其中参数channelId:{},channelName:{},recordingId:{}", channelId, channelName, recordingId);
    }

    public void onUserOffline(Integer channelId,Long recordingId,Long uid,Integer reason){
        log.info(">>>>回调onUserOffline,其中参数channelId:{},recordingId:{},uid:{},reason:{}",channelId,recordingId,uid,reason);
    }

    public void onUserJoined(Integer channelId,Long recordingId,Long uid,String recordingDir){
        log.info(">>>>回调onUserJoined,其中参数channelId:{},recordingId:{},uid:{},recordingDir:{}",channelId,recordingId,uid,recordingDir);
    }
}
