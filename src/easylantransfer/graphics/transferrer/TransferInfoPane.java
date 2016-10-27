package easylantransfer.graphics.transferrer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TransferInfoPane extends JTabbedPane {
    public TransferInfoPane() {
        addTab("Information", new FileInfoPanel());
        addTab("Speed", new SpeedInfoPanel());
    }
}
