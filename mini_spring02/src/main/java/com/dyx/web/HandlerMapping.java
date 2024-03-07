package com.dyx.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Author: dyx1234
 * @Date: 2024-03-05-21:48
 * @Description: 映射器，用来保存URL和Method的对应关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerMapping {

    private Object controller;
    private Method method;
    private Pattern pattern;

}
