package org.litespring.bean;

/**
 * xml中property的value属性转换器
 */
public interface PropertyEditor {

    Object getValue();

    void setAsText(String text);

}
