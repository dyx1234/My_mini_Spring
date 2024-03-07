package com.dyx.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: dyx1234
 * @Date: 2024-03-05-21:49
 * @Description: 用于存储视图名称和数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelAndView {
    private String viewName;;
    private Map<String, ?> model;

}
