package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PictureUrlConfig implements WebMvcConfigurer {
    @Value("${PATH}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+path);
        registry.addResourceHandler("/team/**").addResourceLocations("file:"+path+"team/");
        registry.addResourceHandler("/member/**").addResourceLocations("file:"+path+"member/");
        registry.addResourceHandler("/manager/**").addResourceLocations("file:"+path+"manager/");
        registry.addResourceHandler("/activity/**").addResourceLocations("file:"+path+"activity/");
        registry.addResourceHandler("/department/**").addResourceLocations("file:"+path+"department/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
