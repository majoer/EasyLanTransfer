package easylantransfer.graphics.transferrer;

import easylantransfer.Core.ConstantConfig;
import easylantransfer.graphics.MainGraphics;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TransferrerCard extends JPanel{
    
    public TransferrerCard(MainGraphics mainGraphics) {
        setLayout(new BorderLayout());
//        setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        
//        c.weightx = 1;
//        c.weighty = 0;
//        c.fill = GridBagConstraints.BOTH;
//        c.gridx = 0;
//        c.gridy = 0;
//        
//        add(new NorthPanel(mainGraphics), c);
//        
//        c.weighty = 1;
//        
//        c.gridy = 1;
        add(new SouthPanel(), BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
//        JFrame f = new JFrame();
//        f.setSize(ConstantConfig.windowSize);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(new TransferrerCard());
//        f.setVisible(true);
    }
}