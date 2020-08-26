package com.sy;

import com.sy.common.interceptor.InterceptorRules;

import com.sy.zd.config.CasConfig;
import com.sy.zd.config.ShiroConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.sy")
@MapperScan({"com.sy.zd.mapper","com.sy.dsz.dao"})
@Import({SpringEsConfig.class, InterceptorRules.class, ShiroConfig.class, CasConfig.class})
public class SyshoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyshoppingApplication.class, args);
    }

}
