package easylantransfer.graphics.settings.network;

import easylantransfer.Core.Config;
import easylantransfer.graphics.settings.SaveablePanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class TransferProtocolPanel extends SaveablePanel {

    private JSpinner tcpListenPort, tcpConnectPort;

    public TransferProtocolPanel() {
        init();
        Border main = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(main, "Connection"));
        setLayout(new GridLayout(2, 2));
        
        add(new JLabel("Listen port"));
        add(tcpListenPort);
        
        add(new JLabel("Connect port"));
        add(tcpConnectPort);
    }

    private void init() {
        tcpListenPort = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getTCP_LISTEN_PORT(),
                        1, 65565, 1));
        tcpConnectPort = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getTCP_CONNECT_PORT(),
                        1, 65565, 1));
    }

    @Override
    public void save() {
        Config c = Config.getInstance();
        c.setTCP_LISTEN_PORT((int) tcpListenPort.getValue());
        c.setTCP_CONNECT_PORT((int) tcpConnectPort.getValue());
    }
}
