package com.scaffold.common.annotation;

import java.lang.annotation.*;

/**
 * @Author danyiran
 * @create 2020/7/1 23:23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipResponseConvert {
}
