package com.wayakeji.alivod.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultAcsClientConfig {

    private final Alivod alivod;

    @Bean
    public void setDefaultAcsClient() {
        //1.创建初始化对象
        DefaultProfile profile = DefaultProfile.getProfile(alivod.getRegionId(), alivod.getAccessKeyId(), alivod.getAccessKeySecret());
        Alivod.client = new DefaultAcsClient(profile);
    }

}
