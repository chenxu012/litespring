package org.litespring.configura.support;

import org.litespring.configura.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingtonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonMap = new ConcurrentHashMap<String, Object>();

    @Override
    public void registrySingletonBean(String className, Object object) {
        Object obj = this.singletonMap.get(className);
        if (obj != null) {
            return;
        }
        singletonMap.put(className, object);
    }

    @Override
    public Object getSingleton(String className) {
        return this.singletonMap.get(className);
    }
}
