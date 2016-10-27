package easylantransfer.graphics.settings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

public abstract class GeneralContentPanel extends SaveablePanel {
    protected GridBagConstraints c;

    public GeneralContentPanel(String title) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        c = new GridBagConstraints();
    }
    
}
