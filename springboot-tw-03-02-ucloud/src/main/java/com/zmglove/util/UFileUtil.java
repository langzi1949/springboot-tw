package com.zmglove.util;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Ucloud-File
 */
@Slf4j
public class UFileUtil {
    ObjectAuthorization OBJECT_AUTHORIZER;
    public UFileUtil(String publicKey,String privateKey){
        OBJECT_AUTHORIZER = new UfileObjectLocalAuthorization(publicKey,privateKey);
    }


    public void upload(String path) throws UfileServerException, UfileClientException {
        File file = new File(path);
        ObjectConfig config = new ObjectConfig("cn-sh2", "ufileos.com");
        PutObjectResultBean response = UfileClient.object(OBJECT_AUTHORIZER,config)
                .putObject(file,"video/mp4")
                .nameAs("/20190815/10002/kobe.mp4")
                .toBucket("zmg-media")
                .execute();
        log.info(">>>>>response = {}", JSON.toJSONString(response));
    }


    public void multiUpload(String path){

    }


}
