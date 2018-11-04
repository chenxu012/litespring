package org.litespring.configura;

public interface SingletonBeanRegistry {

    void registrySingletonBean(String className, Object object);

    Object getSingleton(String className);

}
