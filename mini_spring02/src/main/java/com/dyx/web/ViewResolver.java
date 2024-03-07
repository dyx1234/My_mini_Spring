package com.dyx.web;

import java.io.File;

/**
 * @Author: dyx1234
 * @Date: 2024-03-05-21:49
 * @Description: 模板引擎解析匹配
 */
public class ViewResolver {
    /**
     * <p>默认解析的文件后缀</p>
     */
    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    /**
     * <p>模板文件的目录</p>
     */
    private File templateRootDir;

    /**
     * <p>视图名称</p>
     */
    private String viewName;

    public ViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getResource(templateRoot).getFile();
        this.templateRootDir = new File(templateRootPath);
    }

    /**
     * <p>解析视图名</p>
     */
    public View resolveViewName(String viewName) {
        this.viewName = viewName;
        if (viewName == null || "".equals(viewName.trim())) {
            return null;
        }
        // 如果viewName没有加上后缀则加上
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : viewName + DEFAULT_TEMPLATE_SUFFIX;
        // 获取对应的模板文件
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        // 通过模板文件返回视图对象
        return new View(templateFile);
    }
}
