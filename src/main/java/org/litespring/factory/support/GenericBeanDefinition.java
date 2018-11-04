package org.litespring.factory.support;

import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class GenericBeanDefinition implements BeanDefinition {

    private final String id;
    private final String className;
    private String scope;
    private boolean isSingleton;
    private boolean isPrototype;
    private List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();


    public GenericBeanDefinition(String id, String className, String scope,List<PropertyValue> propertyValueList) {
        this.id = id;
        this.className = className;
        this.scope = scope;
        this.isSingleton = SINGLETION_ATTRBUTE.equals(this.scope) || SINGLETION_DEFAULT.equals(this.scope);
        this.isPrototype = PROTOTYPE_ARRTBUTE.equals(this.scope);
        this.propertyValueList = propertyValueList;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValueList;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.isSingleton = SINGLETION_ATTRBUTE.equals(this.scope) || SINGLETION_DEFAULT.equals(this.scope);
        this.isPrototype = PROTOTYPE_ARRTBUTE.equals(this.scope);
    }

    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }

    @Override
    public boolean isPrototype() {
        return this.isPrototype;
    }

    public String getClassName() {
        return className;
    }

}
