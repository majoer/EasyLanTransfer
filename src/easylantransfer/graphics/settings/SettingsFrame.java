package easylantransfer.graphics.settings;

import easylantransfer.Core.Config;
import easylantransfer.Core.ConstantConfig;
import easylantransfer.Core.Core;
import easylantransfer.graphics.StaticGraphicFunctions;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.text.Position;

public class SettingsFrame extends JFrame {
    private ContentPanel contentPanel;
    private Core core;
    
    public SettingsFrame(Core core, Dimension size, Point pos) {
        this.core = core;
        setSize(ConstantConfig.SETTINGS_FRAME_SIZE);
        setLocation(StaticGraphicFunctions.bestPosition(size, getSize(), pos));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.weighty = 1;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        contentPanel = new ContentPanel();
        add(new NavigationPanel(contentPanel), c);
        c.weightx = 1;
        c.gridheight = 1;
        c.gridx = 1;
        add(contentPanel, c);
        
        c.weighty = 0;
        c.gridy = 1;
        add(new ConfirmPanel(this), c);
        
    }
    ContentPanel getContentPanel() {
        return contentPanel;
    }

    public void restartCore() {
        core.stop();
        core.start();
        
    }
    
}
