package com.test.controller;

import com.dyx.annotation.Controller;
import com.dyx.annotation.RequestMapping;
import com.dyx.annotation.RequestParam;
import com.dyx.web.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-22:43
 * @Description:
 */

@Controller
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(@RequestParam("author") String author) {
        Map<String, Object> model = new HashMap<>();
        model.put("author", author);
        return new ModelAndView("test", model);
    }

    @RequestMapping("/exception")
    public void exception() {
        int i = 1/0;
    }
}
