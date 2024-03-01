package com.spring;

import com.spring.Annotation.Component;
import com.spring.Annotation.ComponentScan;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dyx1234
 * @Date: 2024-03-01-10:27
 * @Description:
 */
public class DyxApplicationContext {
    private Class configClass;
    private Map<String, Object> beanMap = new HashMap<>();

    public DyxApplicationContext(Class configClass) throws ClassNotFoundException {
        this.configClass = configClass;

        // 获取扫描路径
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        path = path.replace(".","/");
        /**
         * 三级类加载器:
         * Bootstrap ---->jre/lib
         * Ext----------->jre/ext/lib
         * App----------->classpath------>
         */
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

                    Class<?> aClass = classLoader.loadClass(className);
                    if (aClass.isAnnotationPresent(Component.class)) {
                        // 有Component注解, 是一个Bean
                        // TODO 加载Bean

                    }
                }

            }
        }

    }

    public Object getBean(String beanName) {
        return null;
    }

}
