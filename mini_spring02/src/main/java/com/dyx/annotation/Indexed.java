package com.dyx.annotation;

import java.lang.annotation.*;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:15
 * @Description: 为spring的模式注解添加索引
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Indexed {
}
