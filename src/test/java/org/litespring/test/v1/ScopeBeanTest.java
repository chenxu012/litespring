package org.litespring.test.v1;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.factory.support.exception.BeanCreateException;
import org.litespring.factory.support.exception.BeanExceptionEnum;
import org.litespring.service.v1.PetPro;
import org.litespring.service.v1.PetStoreService;

import java.io.StringReader;
import java.util.Iterator;

public class ScopeBeanTest {

    /**
     * 测试单例
     */
    @Test
    public void testSingletonBean() {

        ApplicationContext context = new ClassPathXmlApplicationContext("pet-store-v1.xml");

        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

        PetStoreService petStoreService1 = (PetStoreService) context.getBean("petStore");

        Assert.assertEquals(petStoreService, petStoreService1);
    }

    /**
     * 测试多例
     */
    @Test
    public void testProBean() {

        ApplicationContext context = new ClassPathXmlApplicationContext("pet-store-v1.xml");

        PetPro petPro = (PetPro) context.getBean("petPro");

        PetPro petPro1 = (PetPro) context.getBean("petPro");

        Assert.assertNotEquals(petPro, petPro1);
    }

    /**
     *
     */
    @Test
    public void testScopeErrorBean() {

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("pet-store-v1-error.xml");
        } catch (BeanCreateException e) {
            System.out.println(e.getMsg());
            return;
        }

        Assert.fail("Bean createtion scope error");
    }

    private static final String BEAN_ID = "id";
    private static final String BEAN = "bean";
    private static final String BEAN_CLASSNAME = "class";
    private static final String BEAN_SCOPE = "scope";

    @Test
    public void testtt() {
        StringReader in = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\">\n" +
                "\n" +
                "    <bean id=\"petStore\" scope=\"singleton\" class=\"org.litespring.service.v1.PetStoreService\">\n" +
                "        chenxu\n" +
                "    </bean>\n" +
                "\n" +
                "    <bean id=\"petPro\" scope=\"prototype\" class=\"org.litespring.service.v1.PetPro\" >\n" +
                "        didiiao\n" +
                "    </bean>\n" +
                "\n" +
                "\n" +
                "</beans>");
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            document = reader.read(in);
            //获取beans元素对象
            Element node = document.getRootElement();
            //获得所有的bean标签
            Iterator<Element> iterator = node.elementIterator(BEAN);
            while (iterator.hasNext()) {
                Element beanEle = iterator.next();
                String id = beanEle.attribute(BEAN_ID) == null ? null : beanEle.attribute(BEAN_ID).getValue();
                String className = beanEle.attribute(BEAN_CLASSNAME) == null ? null : beanEle.attribute(BEAN_CLASSNAME).getValue();
                String scope = beanEle.attribute(BEAN_SCOPE) == null ? "" : beanEle.attribute(BEAN_SCOPE).getValue();


                System.out.println("id=" + id);
                System.out.println("className=" + className);
                System.out.println("scope=" + scope);

                System.out.println("text ==" + beanEle.getText());
            }

        } catch (BeanCreateException e) {
            throw e;
        } catch (Exception e) {
            throw new BeanCreateException(BeanExceptionEnum.BEAN_READ);
        }
    }


}
