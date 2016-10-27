package easylantransfer.graphics.browser;

import easylantransfer.Core.Config;
import easylantransfer.Core.Core;
import easylantransfer.graphics.MainGraphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import util.Randomizer;

public class NorthPanel extends JPanel implements ActionListener, FocusListener, DocumentListener {

    private final Core core;
    private final JTextField usernameField, portField;
    private JTextField[] ipFields;

    public NorthPanel(Core core) {
        this.core = core;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.insets = new Insets(0, 5, 5, 5);

        add(new JLabel("Select username", SwingConstants.CENTER), c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        usernameField = new JTextField(Config.getInstance().getUser().getUsername());
        usernameField.getDocument().addDocumentListener(this);
        add(usernameField, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 5;
        add(new JLabel("Direct connect (IP-Address : port)", SwingConstants.CENTER), c);

        c.weightx = 1;
        c.ipadx = 30;
        c.gridy = 3;
        c.gridwidth = 1;
        addIpFields(c);

        c.gridx = 4;
        c.insets = new Insets(0, -2, 5, 5);
        add(new JLabel(":"), c);

        c.insets = new Insets(0, 5, 5, 5);
        c.ipadx = 40;
        portField = new JTextField();
        portField.addFocusListener(this);
        portField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), "nextField");
        portField.getActionMap().put("nextField", createNextAction());
        ((PlainDocument) portField.getDocument()).setDocumentFilter(new PortFilter());
        add(portField, c);

        c.weightx = 0.5;
        c.ipadx = 0;
        c.gridwidth = 5;
        c.gridx = 0;

        JButton connect = new JButton("Connect");
        connect.setActionCommand("connect");
        connect.addActionListener(this);
        c.gridy++;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5, 0, 5);
        add(connect, c);
    }

    public void refreshIP(String ip, int port) {
        String[] split = ip.split("\\.");
        if (split.length == 4) {
            for (int i = 0; i < ipFields.length; i++) {
                ipFields[i].setText(split[i]);
            }
        }
        if (port >= 0 && port <= 65565) {
            portField.setText("" + port);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "connect":
                connect();
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == portField) {
            portField.select(0, portField.getText().length());
        } else {
            int i = getSelectedIpField();
            ipFields[i].select(0, ipFields[i].getText().length());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        usernameChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        usernameChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        usernameChanged();
    }

    private void addIpFields(GridBagConstraints c) {
        ipFields = new JTextField[4];

        for (int i = 0; i < 4; i++) {
            c.gridx = i;
            ipFields[i] = new JTextField();
            ipFields[i].addFocusListener(this);
            ipFields[i].getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), "nextField");
            ipFields[i].getActionMap().put("nextField", createNextAction());

            IpFilter filter = new IpFilter();
            ((PlainDocument) ipFields[i].getDocument()).setDocumentFilter(filter);

            add(ipFields[i], c);
        }
    }

    private String genIp() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ipFields.length; i++) {
            sb.append(ipFields[i].getText());
            if (i != ipFields.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private void connect() {
        String ip = genIp();
        if (!core.connect(ip, Integer.parseInt(portField.getText()))) {
            JOptionPane.showMessageDialog(this, "Could not connect to " + ip);
        }
    }

    private Action createNextAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = getSelectedIpField();
                int field = (i + 1) % (ipFields.length + 1);
                System.out.println(field);
                if (field < ipFields.length) {
                    ipFields[field].requestFocus();
                } else {
                    portField.requestFocus();
                }
            }
        };
    }

    private int getSelectedIpField() {
        for (int i = 0; i < ipFields.length; i++) {
            if (ipFields[i].isFocusOwner()) {
                return i;
            }
        }
        return -1;
    }

    private void usernameChanged() {
        if (usernameField.getText().length() > 0) {
            if (!core.isRunning()) {
                core.start();
            }
        } else {
            core.stop();
        }
        Config.getInstance().getUser().setUsername(usernameField.getText());
    }
}
