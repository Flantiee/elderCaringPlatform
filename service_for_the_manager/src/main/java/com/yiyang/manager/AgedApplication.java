package com.yiyang.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@ComponentScan
public class AgedApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgedApplication.class,args);
        log.info("项目启动成功》》》》》》》》》");
    }
}
