package com.zmglove.util;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.api.object.multi.MultiUploadInfo;
import cn.ucloud.ufile.api.object.multi.MultiUploadPartState;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.MultiUploadResponse;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.bean.base.BaseResponseBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import cn.ucloud.ufile.util.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ucloud-File
 */
@Slf4j
public class UFileUtil {
    ObjectConfig config = new ObjectConfig("cn-sh2", "ufileos.com");
    ObjectAuthorization OBJECT_AUTHORIZER;
    public UFileUtil(String publicKey,String privateKey){
        OBJECT_AUTHORIZER = new UfileObjectLocalAuthorization(publicKey,privateKey);
    }


    public void upload(String path) throws UfileServerException, UfileClientException {
        File file = new File(path);

        PutObjectResultBean response = UfileClient.object(OBJECT_AUTHORIZER,config)
                .putObject(file,"video/mp4")
                .nameAs("/20190815/10002/kobe.mp4")
                .toBucket("zmg-media")
                .execute();
        log.info(">>>>>response = {}", JSON.toJSONString(response));
    }


    /**
     * 分片上传
     * @param path
     */
    public void multiUpload(String path) throws UfileServerException, UfileClientException {
        File file = new File(path);
        ObjectConfig config = new ObjectConfig("cn-sh2", "ufileos.com");
        MultiUploadInfo state = UfileClient.object(OBJECT_AUTHORIZER,config)
                .initMultiUpload("/20190819/1003/james.mp4","video/mp4","zmg-media")
                .execute();
        if(state ==null){
            return;
        }

        List<MultiUploadPartState> partStates = multiUpload(file, state);
        // 若上传分片结果列表为空，则失败，需中断上传操作。否则完成上传
        if (partStates == null || partStates.isEmpty()) {
            abortMultiUpload(state);
        } else {
            finishMultiUpload(state, partStates);
        }

    }

    private List<MultiUploadPartState> multiUpload(File file, MultiUploadInfo state) {
        List<MultiUploadPartState> partStates = null;
        byte[] buffer = new byte[state.getBlkSize()];
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int len = 0;
            int count = 0;

            partStates = new ArrayList<>();
            // 将数据根据state中指定的大小进行分片
            while ((len = is.read(buffer)) > 0) {
                final int index = count++;
                byte[] sendData = Arrays.copyOf(buffer, len);
                int uploadCount = 0;

                // 可支持重试3次上传
                while (uploadCount < 3) {
                    try {
                        MultiUploadPartState partState = UfileClient.object(OBJECT_AUTHORIZER, config)
                                .multiUploadPart(state, sendData, index)
                                /**
                                 * 指定progress callback的间隔
                                 */
//                                .withProgressConfig(ProgressConfig.callbackWithPercent(50))
                                /**
                                 * 配置进度监听
                                 */
                                .setOnProgressListener(new OnProgressListener() {
                                    @Override
                                    public void onProgress(long bytesWritten, long contentLength) {
                                    }
                                })
                                .execute();
                        if (partState == null) {
                            uploadCount++;
                            continue;
                        }

                        partStates.add(partState);
                        break;
                    } catch (UfileClientException e) {
                        e.printStackTrace();
                        // 尝试次数+1
                        uploadCount++;
                    } catch (UfileServerException e) {
                        e.printStackTrace();
                        // 尝试次数+1
                        uploadCount++;
                    }
                }

                if (uploadCount == 3)
                    return null;
            }

            return partStates;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(is);
        }

        return null;
    }

    private void abortMultiUpload(MultiUploadInfo info) {
        try {
            BaseResponseBean abortRes = UfileClient.object(OBJECT_AUTHORIZER, config)
                    .abortMultiUpload(info)
                    .execute();
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
    }

    private MultiUploadResponse finishMultiUpload(MultiUploadInfo state, List<MultiUploadPartState> partStates) {
        try {
            MultiUploadResponse res = UfileClient.object(OBJECT_AUTHORIZER, config)
                    .finishMultiUpload(state, partStates)
                    .execute();
            return res;
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return null;
    }



}
