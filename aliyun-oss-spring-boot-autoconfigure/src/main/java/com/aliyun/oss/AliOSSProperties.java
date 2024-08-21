package com.aliyun.oss;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String ENDPOINT;
    private String ACCESS_KEY_ID;
    private String SECRET_ACCESS_KEY;
    private String BUCKET_NAME;
}
