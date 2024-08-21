package com.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public class AliOssUtil {


    private AliOSSProperties aliOSSProperties;


    public AliOSSProperties getAliOSSProperties(){
        return aliOSSProperties;
    }
    public void setAliOSSProperties(AliOSSProperties aliOSSProperties){
        this.aliOSSProperties=aliOSSProperties;
    }
    //上传文件,返回文件的公网访问地址
    public String uploadFile(String objectName, InputStream inputStream){
        // 创建OSSClient实例。
        String ENDPOINT=aliOSSProperties.getENDPOINT();
        String ACCESS_KEY_ID = aliOSSProperties.getACCESS_KEY_ID();
        String SECRET_ACCESS_KEY = aliOSSProperties.getSECRET_ACCESS_KEY();
        String BUCKET_NAME = aliOSSProperties.getBUCKET_NAME();

        OSS ossClient = new OSSClientBuilder().build(ENDPOINT,ACCESS_KEY_ID,SECRET_ACCESS_KEY);
        //公文访问地址
        String url = "";
        try {
            // 创建存储空间。
            ossClient.createBucket(BUCKET_NAME);
            ossClient.putObject(BUCKET_NAME, objectName, inputStream);
            url = "https://"+BUCKET_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
