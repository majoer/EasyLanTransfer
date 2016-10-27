package easylantransfer.graphics.transferrer;

import easylantransfer.instance.FileManagementPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SouthPanel extends JPanel {

    public SouthPanel() {
        add(FileManagementPane.getInstance());
        setLayout(new GridLayout(2, 1));
        add(new TransferInfoPane());
    }
}
