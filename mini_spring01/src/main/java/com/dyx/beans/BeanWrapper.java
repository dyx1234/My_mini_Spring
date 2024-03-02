package com.dyx.beans;

import lombok.Data;

/**
 * @Author: dyx1234
 * @Date: 2024-03-02-13:45
 * @Description: 用于封装创建后的对象实例（bean）
 */

public class BeanWrapper {
    /**
     * <p>回由该对象包装的bean实例</p>
     */
    private Object wrappedInstance;

    public BeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    /**
     * <p>返回包装的bean实例的类型</p>
     */
    private Class<?> wrappedClass;

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public void setWrappedInstance(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }

    public void setWrappedClass(Class<?> wrappedClass) {
        this.wrappedClass = wrappedClass;
    }
}
