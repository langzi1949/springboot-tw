package com.zmglove.web;

import com.zmglove.model.AgoraChannel;
import com.zmglove.service.RecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/16 22:31
 **/
@RestController
@Slf4j
@RequestMapping(value = "/recording",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecordingController {
    @Autowired
    private RecordingService recordingService;

    @PostMapping(value = "/create")
    public String createRecording(@RequestBody AgoraChannel channel) throws Exception{
        return recordingService.createRecording(channel);
    }
}
