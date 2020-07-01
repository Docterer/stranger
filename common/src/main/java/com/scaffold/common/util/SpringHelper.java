package com.scaffold.common.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author danyiran
 * @create 2020/7/1 23:26
 */
@Component
@Slf4j
public class SpringHelper implements ApplicationContextAware {

    private static ApplicationContext ac;

    private SpringHelper() {
    }

    @Override
    @SuppressFBWarnings({"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"})
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
        log.info("spring application context initialized...");
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
