package com.dyx;

import com.spring.DyxApplicationContext;

/**
 * @Author: dyx1234
 * @Date: 2024-03-01-10:27
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {

        DyxApplicationContext dyxApplicationContext = new DyxApplicationContext(AppConfig.class);

        Object userService = dyxApplicationContext.getBean("userService");

    }
}
