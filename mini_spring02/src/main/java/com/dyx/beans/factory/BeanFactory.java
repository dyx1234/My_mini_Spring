package com.dyx.beans.factory;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:17
 * @Description: spring顶层接口
 */
public interface BeanFactory {

    Object getBean(String beanName);
}
