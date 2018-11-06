package org.litespring.bean;

public class PropertyEditorSupport implements PropertyEditor{

    private Object value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setAsText(String text) {

    }

    public void setValue(Object value) {
        this.value = value;
    }
}
