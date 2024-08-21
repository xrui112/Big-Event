package com.aliyun.oss;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(AliOSSProperties.class)
public class AliOSSAutoconfiguration {
    @Bean
    public AliOssUtil aliOssUtil(AliOSSProperties aliOSSProperties){
        AliOssUtil aliOssUtil = new AliOssUtil();
        aliOssUtil.setAliOSSProperties(aliOSSProperties);
        return aliOssUtil;
    }


}
