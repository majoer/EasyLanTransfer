package easylantransfer.graphics.settings;

import easylantransfer.Core.ConstantConfig;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

public class NavigationPanel extends JPanel implements ActionListener {

    private ContentPanel cp;
    private JToggleButton[] buttons;

    public NavigationPanel(ContentPanel cp) {
        this.cp = cp;
        int len = ConstantConfig.settings.length;
        buttons = new JToggleButton[len];
        
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Navigation"));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3, 0, 0, 0);
        c.weightx = 1;
        c.weighty = 0;
        for (int i = 0; i < len - 1; i++) {
            addButton(c, i);
        }
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.NORTH;
        addButton(c, len - 1);
        buttons[0].setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton b = (JToggleButton) e.getSource();
        if(b.isSelected()) {
            clear(b);
            cp.swap(e.getActionCommand());
        } else {
            b.setSelected(true);
        }
    }
    
    private void clear(JToggleButton except) {
        for (int i = 0; i < buttons.length; i++) {    
            if(buttons[i] != except) {
                buttons[i].setSelected(false);
            }
        }
    }
    
    private void addButton(GridBagConstraints c, int i) {    
            c.gridy = i;
            buttons[i] = new JToggleButton(ConstantConfig.settings[i]);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
//            buttons[i].setContentAreaFilled(false);
            buttons[i].addActionListener(this);
            add(buttons[i], c);
        
    }
}
