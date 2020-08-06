package com.scaffold.auth.main;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @Author danyiran
 * @create 2020/8/5 13:16
 */
@SpringBootApplication(scanBasePackages = "com.scaffold.auth")
@MapperScan(basePackages = "com.scaffold.auth.persistence")
public class MainApplication {

    private static final transient Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        log.debug("Main starts to run, args: {}", Arrays.toString(args));

        SpringApplication sa = new SpringApplication(MainApplication.class);
        sa.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = sa.run(args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        log.debug("all the bean definition names are: {}", Arrays.toString(beanDefinitionNames));
        log.info("-------------- stranger main application started up --------------");
    }
}
