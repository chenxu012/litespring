package org.litespring.bean;

import java.util.HashMap;
import java.util.Map;

public class SimpleTypeConverter implements TypeConverter {

    private Map<Class<?>, PropertyEditor> typeMap = new HashMap<Class<?>, PropertyEditor>();

    @Override
    public <T> T converIfNecessary(Object value, Class<T> type) {
        if (!(value instanceof String)) {
            return (T)value;
        }
        if (value instanceof String && String.class == type) {
            return (T)value;
        }
        String str = (String)value;
        if (typeMap.isEmpty()) {
            typeMap.put(Integer.class, new CustomNumberEditor(Integer.class, true));
            typeMap.put(int.class, new CustomNumberEditor(int.class, true));
            typeMap.put(Boolean.class, new CustomBooleanEditor(Boolean.class, true));
            typeMap.put(boolean.class, new CustomBooleanEditor(boolean.class, true));
        }

        PropertyEditor propertyEditor = typeMap.get(type);
        propertyEditor.setAsText(str);
        return (T)propertyEditor.getValue();
    }
}
