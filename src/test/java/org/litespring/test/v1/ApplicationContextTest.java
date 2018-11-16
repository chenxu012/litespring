package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemAppcationContext;
import org.litespring.service.v1.PetStoreService;

import java.io.FileNotFoundException;

public class ApplicationContextTest {

    @Test
    public void testClassPathXmlApplicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("pet-store-v1.xml");

        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(petStoreService);
        System.out.println(22);

    }

    @Test
    public void testFileSystemAppcationContext() {
        ApplicationContext context = null;
        try {
            context = new FileSystemAppcationContext("E:\\test\\pet-store-v1.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(petStoreService);

    }

}
