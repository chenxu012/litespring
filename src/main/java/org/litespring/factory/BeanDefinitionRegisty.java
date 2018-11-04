package org.litespring.factory;

import org.litespring.bean.BeanDefinition;

public interface BeanDefinitionRegisty {

    /**
     * 根据id获得Bean的定义
     * @param id
     * @return
     */
    BeanDefinition getBeanDefinition(String id);

    /**
     * 向BeanFactory注册BeanDefinition
     * @param filePath
     */
    void registyBeanDefiniton(String filePath);

}
