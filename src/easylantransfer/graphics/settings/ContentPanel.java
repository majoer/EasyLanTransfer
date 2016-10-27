package easylantransfer.graphics.settings;

import easylantransfer.graphics.settings.disk.DiskContentPanel;
import easylantransfer.graphics.settings.network.NetworkContentPanel;
import easylantransfer.Core.ConstantConfig;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
    
    private final CardLayout card;
    private final SaveablePanel[] saveables;
    
    public ContentPanel() {
        setLayout(card = new CardLayout());
        
        saveables = new GeneralContentPanel[2];
        
        add(saveables[0] = new NetworkContentPanel(ConstantConfig.settings[0]),
                ConstantConfig.settings[0]);
        add(saveables[1] = new DiskContentPanel(ConstantConfig.settings[1]),
                ConstantConfig.settings[1]);
        
    }
    
    public void swap(String card) {
        this.card.show(this, card);
    }

    void save() {
        for(SaveablePanel s : saveables) {
            s.save();
        }
    }
}
