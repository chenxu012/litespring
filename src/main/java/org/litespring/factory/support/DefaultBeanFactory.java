package org.litespring.factory.support;

import javafx.beans.property.SetProperty;
import org.litespring.bean.*;
import org.litespring.configura.support.DefaultSingtonBeanRegistry;
import org.litespring.factory.ConfigurableBeanFactory;
import org.litespring.factory.support.exception.BeanCreateException;
import org.litespring.factory.support.exception.BeanExceptionEnum;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingtonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistory {

    //Bean定义的map
    private Map<String, GenericBeanDefinition> beanMap = new ConcurrentHashMap<String, GenericBeanDefinition>();

    private ClassLoader classLoader;

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanMap.get(id);
    }

    @Override
    public void registory(String id, String className, String scope, List<PropertyValue> propertyValueList) {
        if (id == null || className == null) {
            return;
        }
        beanMap.put(id, new GenericBeanDefinition(id, className, scope, propertyValueList));
    }

    @Override
    public Object getBean(String id) {
        Object bean = null;
        try {
            BeanDefinition bd = this.getBeanDefinition(id);
            if (bd == null) {
                return null;
            }

            String className = bd.getClassName();

            //多例
            if (bd.isPrototype()) {
                bean = createBean(bd);
                return bean;
            }

            //单例
            if (bd.isSingleton()) {
                bean = super.getSingleton(className);
                if (bean == null) {
                    bean = createBean(bd);
                    super.registrySingletonBean(className, bean);
                }
                return bean;
            }
            return bean;

        } catch (Exception e) {
            throw new BeanCreateException(BeanExceptionEnum.BEAN_CREATE);
        }
    }

    private Object createBean(BeanDefinition bd) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object bean;
        Class<?> beanClass = getClassLoader().loadClass(bd.getClassName());
        bean = beanClass.newInstance();

        //设置属性
        populateBean(bd, bean);


        return bean;
    }

    /**
     * 设置属性
     * @param bd
     * @param bean
     */
    private void populateBean(BeanDefinition bd, Object bean) {
        List<PropertyValue> propertyValues = bd.getPropertyValues();
        if (propertyValues.isEmpty()) {
            return;
        }
        try {
            for (PropertyValue propertyValue : propertyValues) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                //解析value
                BeanDefintionValueResolver beanDefintionValueResolver = new BeanDefintionValueResolver(this);
                Object convertValue = beanDefintionValueResolver.resovlerValue(value);

                //对于string类型的convertValue还需要进行转换
                SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();

                //执行set方法
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getName().equals(name)) {
                        Object endValue = simpleTypeConverter.converIfNecessary(convertValue, propertyDescriptor.getPropertyType());
                        propertyDescriptor.getWriteMethod().invoke(bean, endValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreateException("加载property错误");
        }
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
