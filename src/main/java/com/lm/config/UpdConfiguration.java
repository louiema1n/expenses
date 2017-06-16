package com.lm.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 上传配置类
 * Created by Louie on 2017-06-16.
 */
@Configuration
public class UpdConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置上传文件大小
        factory.setMaxFileSize("10MB");
        // 设置文件上传总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();

    }
}
