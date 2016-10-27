package easylantransfer.instance;

import Entity.FileDescription;
import javax.swing.JLabel;

public class FileInfo {

    private static FileInfo instance;
    private JLabel name, size, lastModified;

    private FileInfo() {
        name = new JLabel("N/A");
        size = new JLabel("N/A");
        lastModified = new JLabel("N/A");
    }

    public static FileInfo getInstance() {
        if (instance == null) {
            instance = new FileInfo();
        }
        return instance;
    }

    public void updateFileInfo(FileDescription fd) {
        name.setText(fd.getName());
        size.setText("" + fd.getSize());
        lastModified.setText("" + fd.getLastModified());
    }

    public JLabel getName() {
        return name;
    }

    public JLabel getSize() {
        return size;
    }

    public JLabel getLastModified() {
        return lastModified;
    }    
    
}
