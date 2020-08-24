package com.sy;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SpringEsConfig {

    /**
     * 解决和SpringDataRedis的冲突
     */
   @PostConstruct
    void init() {

        System.setProperty("es.set.netty.runtime.available.processors", "false");

    }
}
