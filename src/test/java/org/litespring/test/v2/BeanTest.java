package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.PetPro;
import org.litespring.service.v1.PetStoreService;

public class BeanTest {

    @Test
    public void testBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("pet-store-v2.xml");

        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

        Assert.assertTrue(petStoreService.getPetPro() instanceof PetPro);
        Assert.assertTrue("测试".equals(petStoreService.getValue()));
        Assert.assertTrue(3 == petStoreService.getVersion());
        Assert.assertTrue(true == petStoreService.isFlag());
        System.out.println("xiaoma");
    }

}
