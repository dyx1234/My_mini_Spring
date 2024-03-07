package com.dyx.annotation;

import java.lang.annotation.*;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:35
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {
    String value() default "";
}
