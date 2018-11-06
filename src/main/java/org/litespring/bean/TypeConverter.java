package org.litespring.bean;

public interface TypeConverter {

    <T> T converIfNecessary(Object value, Class<T> type);

}
