package Entity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FileDescription implements Serializable {

    private String name;
    private long size, lastModified;
    private boolean directory;
    private List<FileDescription> children;
    private Status status;

    public FileDescription() {
    }

    public FileDescription(File file) {
        children = new ArrayList<>();
        name = file.getName();
        size = file.length();
        status = Status.UNSENT;
        directory = file.isDirectory();
        lastModified = file.lastModified();
        
        if (directory) {
            digHierarchy(file, this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FileDescription) {
            FileDescription fd = (FileDescription) obj;

            if (name.equals(fd.name)
                    && size == fd.size
                    && directory == fd.directory
                    && status == fd.status
                    && lastModified == fd.lastModified) {
                return true;
            }
        }

        return false;
    }

    private void digHierarchy(File parent, FileDescription fdParent) {
        for (File child : parent.listFiles()) {
            fdParent.children.add(new FileDescription(child));
        }
    }

    

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    
    
    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public List<FileDescription> getChildren() {
        return children;
    }

    public void setChildren(List<FileDescription> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
