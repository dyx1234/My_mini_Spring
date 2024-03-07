package com.dyx.annotation;

import java.lang.annotation.*;

/**
 * @Author: dyx1234
 * @Date: 2024-03-05-21:50
 * @Description:
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
