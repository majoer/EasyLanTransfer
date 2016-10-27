package easylantransfer.graphics.settings.network;

import easylantransfer.graphics.settings.GeneralContentPanel;
import easylantransfer.graphics.settings.SaveablePanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NetworkContentPanel extends GeneralContentPanel {
    private SaveablePanel[] saveables;
    
    public NetworkContentPanel(String title) {
        super(title);
        
        saveables = new SaveablePanel[2];
        
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        
        
        add(saveables[0] = new DiscoverProtocolPanel(), c);
        c.gridx = 1;
        add(saveables[1] = new TransferProtocolPanel(), c);
    }

    @Override
    public void save() {
        for(SaveablePanel s : saveables) {
            s.save();
        }
    }
   
}
