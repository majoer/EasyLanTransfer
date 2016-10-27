package easylantransfer.graphics.transferrer;

import Entity.FileDescription;
import easylantransfer.instance.FileInfo;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FileInfoPanel extends JPanel {

    public FileInfoPanel() {
        FileInfo f = FileInfo.getInstance();
        
        setLayout(new GridLayout(1, 2));

        Box left = Box.createVerticalBox();
        addHorizontalBox(left, "Name", f.getName());
        addHorizontalBox(left, "Size", f.getSize());
        addHorizontalBox(left, "Last Modified", f.getLastModified());

        add(left);

        Box right = Box.createVerticalBox();
        
        add(right);
    }

    private void addHorizontalBox(Box vertical, String name, JLabel label) {
        Box horizontal = Box.createHorizontalBox();
        horizontal.add(new JLabel(name + ": "));
        horizontal.add(label);
        horizontal.add(Box.createGlue());
        vertical.add(horizontal);
    }
}
