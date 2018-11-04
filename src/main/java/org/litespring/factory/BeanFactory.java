package org.litespring.factory;

public interface BeanFactory {

    /**
     * 根据id获得Bean对象
     * @param id
     * @return
     */
    Object getBean(String id);
}
