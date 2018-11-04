package org.litespring.io.support;

import org.litespring.io.Resource;

import java.io.InputStream;

public class ClassPathResource implements Resource {

    private InputStream inputStream;

    public ClassPathResource(String filePath, ClassLoader classLoader) {
        this.inputStream = classLoader.getResourceAsStream(filePath);
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }
}
