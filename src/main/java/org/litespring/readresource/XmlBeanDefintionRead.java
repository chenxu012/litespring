package org.litespring.readresource;

import com.google.common.base.Strings;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PropertyValue;
import org.litespring.bean.RunTimeBeanRenference;
import org.litespring.bean.TypedStringValue;
import org.litespring.io.Resource;
import org.litespring.factory.support.DefaultBeanFactory;
import org.litespring.factory.support.exception.BeanCreateException;
import org.litespring.factory.support.exception.BeanExceptionEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlBeanDefintionRead {

    private DefaultBeanFactory defaultBeanFactory;
    private static final String BEAN_ID = "id";
    private static final String BEAN = "bean";
    private static final String BEAN_CLASSNAME = "class";
    private static final String BEAN_SCOPE = "scope";
    private static final String PROPERTY = "property";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_REF = "ref";
    private static final String PROPERTY_VALUE = "value";

    public XmlBeanDefintionRead(DefaultBeanFactory beanFactory) {
        this.defaultBeanFactory = beanFactory;
    }

    public void loadFile(Resource resource) {
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            document = reader.read(resource.getInputStream());
            //获取beans元素对象
            Element node = document.getRootElement();
            //获得所有的bean标签
            Iterator<Element> iterator = node.elementIterator(BEAN);
            while (iterator.hasNext()) {
                Element beanEle = iterator.next();
                String id = beanEle.attribute(BEAN_ID) == null ? null : beanEle.attribute(BEAN_ID).getValue();
                String className = beanEle.attribute(BEAN_CLASSNAME) == null ? null : beanEle.attribute(BEAN_CLASSNAME).getValue();
                String scope = beanEle.attribute(BEAN_SCOPE) == null ? "" : beanEle.attribute(BEAN_SCOPE).getValue();

                //校验scope
                if ((!scope.equals(BeanDefinition.SINGLETION_ATTRBUTE) && !scope.equals(BeanDefinition.PROTOTYPE_ARRTBUTE)
                        && !scope.equals(BeanDefinition.SINGLETION_DEFAULT))) {
                    throw new BeanCreateException(BeanExceptionEnum.BEAN_SCOPE_READ);
                }

                //加载bean下的property节点
                Iterator<Element> iteratorPro = beanEle.elementIterator(PROPERTY);
                List<PropertyValue> propertyValueList = readProperty(iteratorPro);

                defaultBeanFactory.registory(id, className, scope, propertyValueList);
            }

        } catch (BeanCreateException e) {
            throw e;
        } catch (Exception e) {
            throw new BeanCreateException(BeanExceptionEnum.BEAN_READ);
        }
    }

    /**
     * 加载bean下的property节点
     *
     * @param iteratorPro
     */
    private List<PropertyValue> readProperty(Iterator<Element> iteratorPro) {
        List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
        while (iteratorPro.hasNext()) {
            Element property = iteratorPro.next();
            String propertyName = property.attribute(PROPERTY_NAME) == null ? null : property.attribute(PROPERTY_NAME).getValue();
            String propertyValue = property.attribute(PROPERTY_VALUE) == null ? null : property.attribute(PROPERTY_VALUE).getValue();
            String propertyRef = property.attribute(PROPERTY_REF) == null ? null : property.attribute(PROPERTY_REF).getValue();

            if (Strings.isNullOrEmpty(propertyName)) {
                throw new BeanCreateException(BeanExceptionEnum.BEAN_SCOPE_READ);
            }

            if (!Strings.isNullOrEmpty(propertyValue)) {
                propertyValueList.add(new PropertyValue(propertyName, new TypedStringValue(propertyValue)));
            } else if (!Strings.isNullOrEmpty(propertyRef)) {
                propertyValueList.add(new PropertyValue(propertyName, new RunTimeBeanRenference(propertyRef)));
            } else {
                throw new BeanCreateException("property还未支持其他方式");
            }

        }
        return propertyValueList;
    }
}
