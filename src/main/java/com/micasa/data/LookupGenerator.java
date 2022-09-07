package com.micasa.data;

public abstract class LookupGenerator<T> implements Generator<T> {

    protected final String filePath;
    protected final String selector;
    protected final FileType fileType;
    protected boolean loaded;


    LookupGenerator(String filePath, FileType fileType, String selector) {
        this.filePath = filePath;
        this.fileType = fileType;
        this.selector = selector;
    }

}
