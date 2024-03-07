package com.dyx.context.annotation;

import com.dyx.annotation.ComponentScan;
import com.dyx.beans.factory.support.BeanDefinitionReader;
import com.dyx.context.support.AbstractApplicationContext;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:47
 * @Description:
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext {
    public AnnotationConfigApplicationContext(Class annotatedClasses) throws Exception {
        // 扫描
        super.reader = new BeanDefinitionReader(getScanPackage(annotatedClasses));
        // 刷新
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        super.refresh();
    }

    /**
     * 获取扫描的包路径
     * @param annotatedClasses
     * @return
     */
    private String getScanPackage(Class annotatedClasses) {
        if(!annotatedClasses.isAnnotationPresent(ComponentScan.class)) {
            throw new RuntimeException("The " + annotatedClasses.getName() + " is not annotated by @ComponentScan");
        }
        ComponentScan componentScan = (ComponentScan) annotatedClasses.getAnnotation(ComponentScan.class);
        return componentScan.value().trim();
    }

}
