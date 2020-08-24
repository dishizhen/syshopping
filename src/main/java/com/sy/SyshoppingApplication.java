package com.sy;

import com.sy.common.interceptor.InterceptorRules;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.sy.zd.dao","com.sy.dsz.dao"})
@Import({SpringEsConfig.class, InterceptorRules.class})
public class SyshoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyshoppingApplication.class, args);
    }

}
