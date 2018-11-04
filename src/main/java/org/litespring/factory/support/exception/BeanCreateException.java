package org.litespring.factory.support.exception;

import org.litespring.exception.BeansException;
import org.litespring.exception.STMExceptionEnum;

public class BeanCreateException extends RuntimeException implements BeansException {

    private String msg;

    public BeanCreateException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BeanCreateException(STMExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.msg = exceptionEnum.getMsg();
    }


    public String getMsg() {
        return msg;
    }
}
