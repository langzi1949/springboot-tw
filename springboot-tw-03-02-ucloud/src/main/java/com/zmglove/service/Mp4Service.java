package com.zmglove.service;

import org.springframework.stereotype.Service;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;

/**
 * 处理MP4视频
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/19 19:45
 **/
@Service
public class Mp4Service {

    public long getTime(String videoPath) throws EncoderException {
        MultimediaInfo info = getMediaInfo(videoPath);
        return info.getDuration();
    }


    public MultimediaInfo getMediaInfo(String videoPath) throws EncoderException {
        File source = new File(videoPath);
        // 创建媒体对象
        MultimediaObject multimediaObject = new MultimediaObject(source);
        // 创建信息对象
        return multimediaObject.getInfo();
    }
}
