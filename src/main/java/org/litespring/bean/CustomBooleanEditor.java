package org.litespring.bean;

import org.litespring.exception.TypeMissException;

public class CustomBooleanEditor extends PropertyEditorSupport {

    private Class<?> classes;
    private boolean flag;

    private static final String VALUE_YES = "yes";
    private static final String VALUE_NO = "no";
    private static final String VALUE_ON = "on";
    private static final String VALUE_OFF = "off";
    private static final String VALUE_TRUE = "true";
    private static final String VALUE_FALSE = "false";
    private static final String VALUE_1 = "1";
    private static final String VALUE_0 = "0";

    public CustomBooleanEditor(Class<?> classes, boolean flag) {
        this.classes = classes;
        this.flag = flag;
    }

    @Override
    public void setAsText(String text)  {
        if ("".equals(text) && !flag) {
            throw new TypeMissException();
        }

        if (VALUE_YES.equals(text) || VALUE_1.equals(text) || VALUE_ON.equals(text) || VALUE_TRUE.equals(text)) {
            setValue(true);
        } else if (VALUE_NO.equals(text) || VALUE_0.equals(text) || VALUE_OFF.equals(text) || VALUE_FALSE.equals(text)) {
            setValue(false);
        }
    }
}
