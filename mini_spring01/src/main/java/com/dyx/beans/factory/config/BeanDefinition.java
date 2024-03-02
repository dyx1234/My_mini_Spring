package com.dyx.beans.factory.config;

import lombok.Data;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:15
 * @Description: 定义了与bean实例化相关的各种信息, 通过BeanDefinition中的信息就可以实例化出一个bean对象
 */

@Data
public class BeanDefinition {

    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;

}
