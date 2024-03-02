package com.dyx.beans.factory.support;

import com.dyx.beans.factory.config.BeanDefinition;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:17
 * @Description:
 */
public class BeanDefinitionReader {
    private List<String> registryBeanClasses = new ArrayList<String>();

    public  BeanDefinitionReader(String scanPackage) throws Exception {
        doScan(scanPackage);
    }

    public void doScan(String scanPackage) throws Exception {
        // 将包名转为文件路径
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        if (url == null) {
            throw new Exception("包" + scanPackage + "不存在！");
        }
        // 把scanPackage下的所有class文件都找出来, 并且存储到registryBeanClasses中
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." +file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    // 如果不是class文件则跳过
                    continue;
                }
                String className = scanPackage + "." + file.getName().replace(".class", "");
                registryBeanClasses.add(className);
            }
        }
    }

    public List<BeanDefinition> loadBeanDefinitions() {
        List<BeanDefinition> result = new ArrayList<BeanDefinition>();
        try {
            for (String className : registryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()){
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    result.add(doCreateBeanDefinition(anInterface.getName(), beanClass.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private BeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
