package com.wayakeji.alivod.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 阿里配置静态类
 *
 * @author hu trace
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "alivod")
public class Alivod {

    static DefaultAcsClient client;

    private Long cateId;

    private String storageLocation;

    private String videoUrlProtocol = "https";

    private String regionId;

    private String accessKeyId;

    private String accessKeySecret;

    /**
     * 获取alivod客户端
     * @return
     */
    public static DefaultAcsClient client() {
        return client;
    }

}
