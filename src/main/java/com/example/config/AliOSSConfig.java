package com.example.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliOSSConfig {

    @Value("${spring.alioss.endpoint}")
    private String endpoint;

    @Value("${spring.alioss.accessKeyId}")
    private String accessKeyId;

    @Value("${spring.alioss.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public OSS ossClient()
    {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
