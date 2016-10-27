package easylantransfer.graphics.browser;

import easylantransfer.Core.Core;
import easylantransfer.graphics.MainGraphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BrowserCard extends JPanel {

    public BrowserCard(Core core) {
        NorthPanel northPanel = new NorthPanel(core);
        SouthPanel southPanel = new SouthPanel(northPanel);
        
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;

        add(northPanel, c);
        
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        add(southPanel, c);
    }
}
