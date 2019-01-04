package com.bing.lan.msj.starter;

import com.bing.lan.msj.MsjApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * https://blog.csdn.net/songhaifengshuaige/article/details/54138023
 *
 * @author lan_bing
 * @date 2019-01-04 13:16
 */
public class TomcatStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MsjApplication.class);
    }
}
