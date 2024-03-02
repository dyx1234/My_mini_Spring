package com.dyx.annotation;

import java.lang.annotation.*;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:14
 * @Description:
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}

