package easylantransfer.graphics.settings.network;

import easylantransfer.Core.Config;
import easylantransfer.graphics.StaticGraphicFunctions;
import easylantransfer.graphics.settings.SaveablePanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class DiscoverProtocolPanel extends SaveablePanel {

    private JSpinner broadcastRate, timeoutRate, udpDetectionPort, udpSenderPort;

    public DiscoverProtocolPanel() {
        init();
        setLayout(new GridLayout(4, 2));
        Border main = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(main, "Discovery"));

        add(new JLabel("Broadcast Rate"));
        add(broadcastRate);
        add(new JLabel("Timeout Rate"));
        add(timeoutRate);
        add(new JLabel("Listening Port"));
        add(udpDetectionPort);
        add(new JLabel("Broadcast Port"));
        add(udpSenderPort);
    }

    private void init() {
        broadcastRate = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getBroadcastRate(),
                        0, 20000, 500));
        timeoutRate = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getBroadcastTimeoutRate(),
                        1, 5, 1));
        udpDetectionPort = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getUDP_DETECTION_PORT(),
                        1, 65535, 1));
        udpSenderPort = new JSpinner(
                new SpinnerNumberModel(Config.getInstance().getUDP_SENDER_PORT(),
                        1, 65535, 1));
    }

    @Override
    public void save() {
        Config c = Config.getInstance();
        c.setBroadcastRate((int) broadcastRate.getValue());
        c.setBroadcastTimeoutRate((int) timeoutRate.getValue());
        c.setUDP_DETECTION_PORT((int) udpDetectionPort.getValue());
        c.setUDP_SENDER_PORT((int) udpSenderPort.getValue());
    }

}
