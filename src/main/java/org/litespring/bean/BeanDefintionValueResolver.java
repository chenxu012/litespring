package org.litespring.bean;

import org.litespring.factory.support.DefaultBeanFactory;
import org.litespring.factory.support.exception.BeanCreateException;

public class BeanDefintionValueResolver {

    private DefaultBeanFactory defaultBeanFactory;

    public BeanDefintionValueResolver(DefaultBeanFactory defaultBeanFactory) {
        this.defaultBeanFactory = defaultBeanFactory;
    }

    public Object resovlerValue(Object value) {
        String ss = "454534535";
        if (value != null) {
            if (value instanceof RunTimeBeanRenference) {
                return defaultBeanFactory.getBean(((RunTimeBeanRenference) value).getBeanName());
            } else if (value instanceof TypedStringValue) {
                return ((TypedStringValue) value).getValue();
            } else {
                throw new BeanCreateException(value + "is not every thing");
            }
        }
        return null;
    }
}
