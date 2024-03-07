package com.test;

import com.dyx.context.ApplicationContext;
import com.dyx.context.annotation.AnnotationConfigApplicationContext;
import com.test.config.ApplicationConfig;
import com.test.service.TestService;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-22:21
 * @Description:
 */
public class ApplicationTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TestService testService =(TestService) applicationContext.getBean("testService");
        testService.echo();
    }

}
