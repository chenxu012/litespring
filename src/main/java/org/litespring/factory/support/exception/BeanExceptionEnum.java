package org.litespring.factory.support.exception;

import org.litespring.exception.STMExceptionEnum;

public enum BeanExceptionEnum implements STMExceptionEnum {

    /**
     * bean创建时出错
     */
    BEAN_CREATE("Bean Create error"),

    /**
     * Bean加载时出错
     */
    BEAN_READ("Bean read error"),

    /**
     * Bean的scope加载错误
     */
    BEAN_SCOPE_READ("Bean scope read error")
    ;

    private String msg;


    BeanExceptionEnum(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
