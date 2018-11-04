package org.litespring.bean;

import java.util.List;

public interface BeanDefinitionRegistory {

    BeanDefinition getBeanDefinition(String id);
    void registory(String id, String className, String scope, List<PropertyValue> propertyValueList);
}
