package easylantransfer.graphics.settings;

import javax.swing.JPanel;

public abstract class SaveablePanel extends JPanel {
    
    public SaveablePanel() {
        
    }

    public abstract void save();
}
