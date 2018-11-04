package org.litespring.context;

import org.litespring.io.Resource;
import org.litespring.factory.support.DefaultBeanFactory;
import org.litespring.readresource.XmlBeanDefintionRead;

import java.io.FileNotFoundException;

public abstract class AbstractAppcationContext implements ApplicationContext {

    protected DefaultBeanFactory defaultBeanFactory;

    protected void loadFile(Resource resource) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefintionRead xmlBeanDefinition = new XmlBeanDefintionRead(defaultBeanFactory);
        xmlBeanDefinition.loadFile(resource);
    }

    protected abstract Resource readResource(String filePath) throws FileNotFoundException;

}
