package com.dyx.beans.factory.support;

import com.dyx.beans.factory.BeanFactory;
import com.dyx.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:17
 * @Description: Bean工厂的实现类
 */
public class DefaultListableBeanFactory implements BeanFactory {

    public final Map<String, BeanDefinition> beanDefinitionMap  = new ConcurrentHashMap<String, BeanDefinition>(16);

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
