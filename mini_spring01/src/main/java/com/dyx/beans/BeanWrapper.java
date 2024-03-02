package com.dyx.beans;

import lombok.Data;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:45
 * @Description: 用于封装创建后的对象实例（bean）
 */

@Data
public class BeanWrapper {

    private Object wrappedInstance;
    private Class wappedClass;

    public BeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
        this.wappedClass = wrappedInstance.getClass();
    }

}
