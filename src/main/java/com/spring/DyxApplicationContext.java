package com.spring;

import com.spring.Annotation.Component;
import com.spring.Annotation.ComponentScan;
import com.spring.Annotation.Scope;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import com.spring.Enum.scope;
/**
 * @Author: dyx1234
 * @Date: 2024-03-01-10:27
 * @Description:
 */
public class DyxApplicationContext {
    private Class configClass;
    // 单例池
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();



    public DyxApplicationContext(Class configClass) {
        this.configClass = configClass;

        // ComponentScan扫描---->扫描路径---->BeanDefinition------>beanDefinitionMap
        scan(configClass);

        for(String beanName : beanDefinitionMap.keySet()){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals(scope.SINGLETON)){
                Object bean = createBean(beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }

    }



    private void scan(Class configClass) {
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        path = path.replace(".","/");
        // 根据扫描路径去加载类
        ClassLoader classLoader = DyxApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                // 通过全路径解析出类className
                String fileName = f.getAbsolutePath();
                // 如果是类文件,进行解析
                if(fileName.endsWith(".class")){
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    className = className.replace("\\",".");

                    try {
                        Class<?> aClass = classLoader.loadClass(className);
                        if (aClass.isAnnotationPresent(Component.class)) {
                            // 有Component注解, 是一个Bean。进行解析并存储为BeanDefinition对象
                            String beanName = aClass.getDeclaredAnnotation(Component.class).value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(aClass);
                            if (aClass.isAnnotationPresent(Scope.class)) {
                                beanDefinition.setScope(aClass.getDeclaredAnnotation(Scope.class).value());
                            }else {
                                beanDefinition.setScope(scope.SINGLETON);
                            }
                            beanDefinitionMap.put(beanName,beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        //TODO 创建bean,待完善
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return clazz;
    }

    public Object getBean(String beanName) {
        if(beanDefinitionMap.contains(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals(scope.SINGLETON)){
                Object o = singletonObjects.get(beanName);
                return o;
            }else {
                //TODO 创建bean对象
            }

        } else {
            // 不存在对应bean
            throw new NullPointerException("不存在此对象");
        }
        return null;
    }

}
