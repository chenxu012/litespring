package org.litespring.context.support;

import org.litespring.context.AbstractAppcationContext;
import org.litespring.io.Resource;
import org.litespring.io.support.ClassPathResource;

public class ClassPathXmlApplicationContext extends AbstractAppcationContext {

    private ClassLoader classLoader;

    public ClassPathXmlApplicationContext(String filePath) {
       loadFile(readResource(filePath));
    }

    @Override
    public Object getBean(String id) {
        return defaultBeanFactory.getBean(id);
    }

    @Override
    protected Resource readResource(String filePath) {
        return new ClassPathResource(filePath, getClassLoader());
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader != null ? getClassLoader() : this.getClass().getClassLoader();
    }
}
