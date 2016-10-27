package easylantransfer.graphics.transferrer;

import easylantransfer.instance.ConnectedRemoteUser;
import easylantransfer.graphics.MainGraphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanel extends JPanel implements ActionListener {
    private MainGraphics mainGraphics;

    public NorthPanel(MainGraphics mainGraphics) {
        this.mainGraphics = mainGraphics; 

        add(ConnectedRemoteUser.getInstance());
        
        JButton disconnect = new JButton("Disconnect");
        disconnect.setActionCommand("disconnect");
        disconnect.addActionListener(this);
        add(disconnect);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "disconnect":
                disconnect();
                break;
        }
    }

    private void disconnect() {
        mainGraphics.disconnect();
    }

    private void send() {
        
    }
}
