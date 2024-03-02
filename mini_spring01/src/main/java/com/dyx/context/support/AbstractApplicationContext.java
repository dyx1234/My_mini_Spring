package com.dyx.context.support;

import com.dyx.annotation.*;
import com.dyx.beans.BeanWrapper;
import com.dyx.beans.factory.config.BeanDefinition;
import com.dyx.beans.factory.support.BeanDefinitionReader;
import com.dyx.beans.factory.support.DefaultListableBeanFactory;
import com.dyx.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:46
 * @Description: 最主要的主类
 * IOC容器的创建、beanDefinition的扫描创建、bean的创建、依赖注入等都在这里完成
 */
public class AbstractApplicationContext extends DefaultListableBeanFactory implements ApplicationContext {
    protected BeanDefinitionReader reader;
    /**
     * 保存单例对象
     */
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();
    /**
     * 保存包装对象
     */
    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, BeanWrapper>();

    public void refresh() throws Exception {
        // 定位
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        // 注册
        doRegisterBeanDefinition(beanDefinitions);
        // 初始化
        doAutowired();
    }

    /**
     * 将BeanDefinition注册到容器中
     * @param beanDefinitions
     * @throws Exception
     */
    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) throws Exception {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    /**
     * 将非懒加载的类初始化
     */
    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        try {
            // 通过bd实例化bean
            Object instance = instantiateBean(beanName, beanDefinition);
            if (null == instance) {
                return null;
            }
            // 封装成BeanWrapper
            BeanWrapper beanWrapper = new BeanWrapper(instance);
            // 保存到IOC容器
            this.factoryBeanInstanceCache.put(beanName, beanWrapper);
            // 注入
            populateBean(instance);
            return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过bd，实例化bean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)) {
                instance = this.factoryBeanObjectCache.get(beanName);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 注入
     */
    public void populateBean(Object instance) {
        Class clazz = instance.getClass();
        // 判断是否有Controller、Service、Component、Repository等注解标记
        if (!(clazz.isAnnotationPresent(Component.class) ||
                clazz.isAnnotationPresent(Controller.class) ||
                clazz.isAnnotationPresent(Service.class) ||
                clazz.isAnnotationPresent(Repository.class))) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 如果属性没有被Autowired标记，则跳过
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            String autowiredBeanName = field.getType().getName();

            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) != null) {
                    field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
                } else {
                    field.set(instance, getBean(BeanDefinitionReader.toLowerFirstCase(field.getType().getSimpleName())));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
