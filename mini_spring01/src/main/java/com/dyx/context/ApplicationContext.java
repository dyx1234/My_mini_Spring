package com.dyx.context;

import com.dyx.beans.factory.BeanFactory;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:47
 * @Description: 容器顶层接口
 */
public interface ApplicationContext extends BeanFactory {
    void refresh() throws Exception;
}
