package org.litespring.bean;

import java.util.List;

/**
 * Bean的定义
 */
public interface BeanDefinition {

    String SINGLETION_ATTRBUTE = "singleton";
    String PROTOTYPE_ARRTBUTE = "prototype";
    String SINGLETION_DEFAULT = "";

    List<PropertyValue> getPropertyValues();

    String getScope();

    void setScope(String scope);

    boolean isSingleton();

    boolean isPrototype();

    String getClassName();
}
