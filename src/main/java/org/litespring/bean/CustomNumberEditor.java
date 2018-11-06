package org.litespring.bean;

import org.litespring.exception.TypeMissException;

/**
 * 数字类型转换器
 */
public class CustomNumberEditor extends PropertyEditorSupport {

    private Class<?> classes;
    private boolean flag;

    public CustomNumberEditor(Class<?> classes, boolean flag) {
        this.classes = classes;
        this.flag = flag;
    }

    @Override
    public void setAsText(String text) {
        if ("".equals(text) && !flag) {
            throw new TypeMissException();
        }

        if (Integer.class == classes) {
            setValue(Integer.valueOf(text));
        } else if (int.class == classes) {
            setValue(Short.valueOf(text));
        } else if (Short.class == classes) {
            setValue(Short.valueOf(text));
        } else if (short.class == classes) {
            setValue(Short.valueOf(text));
        } else if (double.class == classes) {
            setValue(Double.valueOf(text));
        } else if (Double.class == classes) {
            setValue(Double.valueOf(text));
        }
    }
}
