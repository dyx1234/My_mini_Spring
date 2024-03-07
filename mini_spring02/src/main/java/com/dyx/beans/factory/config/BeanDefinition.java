package com.dyx.beans.factory.config;

import lombok.Data;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:15
 * @Description: 定义了与bean实例化相关的各种信息, 通过BeanDefinition中的信息就可以实例化出一个bean对象
 */

@Data
public class BeanDefinition {
    /**
     * <p>bean对应的全类名</p>
     */
    private String beanClassName;

    /**
     * <p>是否懒加载</p>
     */
    private boolean lazyInit = false;

    /**
     * <p>保存在IOC容器时的key值</p>
     */
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
