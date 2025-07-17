package org.example.tomcatdemo.models;

import java.io.Serializable;

public class FileInfo implements Serializable {
    private String name;
    private long size;
    private String path;

    public FileInfo(String name, long size, String path) {
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
    public String getPath() { return path; }
}