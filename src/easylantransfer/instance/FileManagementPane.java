package easylantransfer.instance;

import easylantransfer.graphics.transferrer.FileManagementPanel;
import easylantransfer.graphics.transferrer.FileManagementPanel;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FileManagementPane extends JTabbedPane implements ChangeListener {

    private static FileManagementPane instance;
    private final FileManagementPanel receive;
    private int unchecked;

    private FileManagementPane() {
        unchecked = 0;
        add("Send", new FileManagementPanel(true));
        add("Receive", receive = new FileManagementPanel(false));
        addChangeListener(this);
    }

    public static FileManagementPane getInstance() {
        if (instance == null) {
            instance = new FileManagementPane();
        }
        return instance;
    }

    public void updateIcon(int count) {
        if (!getSelectedComponent().equals(receive)) {
            unchecked += count;
            setTitleAt(indexOfComponent(receive), "Receive: " + unchecked);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (getSelectedComponent().equals(receive)) {
            clear();
        }
    }

    public void clear() {
        unchecked = 0;
        setTitleAt(getSelectedIndex(), "Receive");

    }
}
