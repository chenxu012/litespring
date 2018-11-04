package org.litespring.bean;

public class RunTimeBeanRenference {

    private String beanName;

    public RunTimeBeanRenference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
