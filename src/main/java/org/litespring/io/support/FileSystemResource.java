package org.litespring.io.support;

import org.litespring.io.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

    private InputStream inputStream;

    public FileSystemResource(String filePath) throws FileNotFoundException {
        this.inputStream = new FileInputStream(filePath);
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }
}
