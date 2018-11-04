package org.litespring.context.support;

import org.litespring.context.AbstractAppcationContext;
import org.litespring.io.Resource;
import org.litespring.io.support.FileSystemResource;

import java.io.FileNotFoundException;

public class FileSystemAppcationContext extends AbstractAppcationContext {

    private ClassLoader classLoader;

    public FileSystemAppcationContext(String filePath) throws FileNotFoundException {
        loadFile(readResource(filePath));
    }

    @Override
    public Object getBean(String id) {
        return defaultBeanFactory.getBean(id);
    }

    @Override
    protected Resource readResource(String filePath) throws FileNotFoundException {
        return new FileSystemResource(filePath);
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
