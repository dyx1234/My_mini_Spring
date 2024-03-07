package com.dyx.annotation;

import java.lang.annotation.*;

/**
 * @Author: dyx1234
 * @Date: 2024-03-05-21:52
 * @Description:
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value() default "";
}
