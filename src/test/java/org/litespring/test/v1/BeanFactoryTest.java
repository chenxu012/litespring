package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.io.support.ClassPathResource;
import org.litespring.io.support.FileSystemResource;
import org.litespring.factory.support.DefaultBeanFactory;
import org.litespring.factory.support.exception.BeanCreateException;
import org.litespring.readresource.XmlBeanDefintionRead;
import org.litespring.service.v1.PetStoreService;

import java.io.FileNotFoundException;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {

        //加载xml
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        XmlBeanDefintionRead xmlBeanDefinition = new XmlBeanDefintionRead(beanFactory);
        xmlBeanDefinition.loadFile(new ClassPathResource("pet-store-v1.xml", this.getClass().getClassLoader()));
        //获得BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v1.PetStoreService", bd.getClassName());
        //获得Bean实例
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStoreService);

    }

    @Test
    public void testInvalidBean() {
        try {
            //加载xml
            DefaultBeanFactory beanFactory = new DefaultBeanFactory();
            XmlBeanDefintionRead xmlBeanDefinition = new XmlBeanDefintionRead(beanFactory);
            xmlBeanDefinition.loadFile(new FileSystemResource("E:\\test\\pet-store-v1.xml"));
            //获得BeanDefinition
            BeanDefinition bd = beanFactory.getBeanDefinition("petStore");

            try {
                PetStoreService petStoreService = (PetStoreService) beanFactory.getBean(null);
            } catch (BeanCreateException e) {
                System.out.println(e.getMsg());
                return;
            }

        } catch (BeanCreateException e) {
            System.out.println(e.getMsg());
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(22);
        Assert.fail("Bean createtion Test error");
        System.out.println(11);
    }


}
