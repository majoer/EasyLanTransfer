package easylantransfer.graphics;

import Entity.User;
import easylantransfer.Core.Config;
import easylantransfer.instance.UserList;
import easylantransfer.Core.ConstantConfig;
import easylantransfer.Core.Core;
import easylantransfer.graphics.browser.BrowserCard;
import easylantransfer.graphics.settings.SettingsFrame;
import easylantransfer.graphics.transferrer.FileManagementPanel;
import easylantransfer.graphics.transferrer.TransferrerCard;
import easylantransfer.instance.FileList;
import easylantransfer.instance.FileManagementPane;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGraphics extends JFrame implements ActionListener {

    private final Core core;
    private SettingsFrame sf;
    private JPanel contentPane;
    private CardLayout cards;
    private final String title = "Easy Lan Transfer";

    private JMenuItem disconnect, settings;

    public MainGraphics(Core core) {
        this.core = core;
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Config.getInstance().getWindowSize());
        setLocation(Config.getInstance().getWindowLocation());
        initMenu();
        initLayout();
    }

    public void connect(User remoteUser) {
        setTitle(title + " - Connected: " + remoteUser.getUsername());
        cards.show(contentPane, ConstantConfig.transferrerCard);
        toggleMenuItems(true);

    }

    public void disconnect() {
        core.disconnect();
        FileList.getInstance(true).unbind();
        FileList.getInstance(false).clear();
        FileList.getInstance(true).clear();
        UserList.getInstance().clear();
        FileManagementPane.getInstance().clear();
        setTitle(title);
        toggleMenuItems(false);
        cards.show(contentPane, ConstantConfig.browserCard);
    }

    public Core getCore() {
        return core;
    }

    public boolean showDialog(InetAddress ip) {
        StringBuilder s = new StringBuilder();
        User u = UserList.getInstance().search(ip);

        if (u == null) {
            s.append(ip.getHostAddress());
        } else {
            s.append(u.getUsername()).append("@").append(ip.getHostAddress());
        }
        s.append(" wants to initiate a transfer session with you! Accept?");
        int choise = JOptionPane.showConfirmDialog(this, s.toString(), "Request", JOptionPane.YES_NO_OPTION);

        return (choise == JOptionPane.YES_OPTION);
    }

    private void initMenu() {
        JMenuBar bar = new JMenuBar();

        //FILE
        JMenu file = new JMenu("File");

        disconnect = new JMenuItem("Disconnect");
        disconnect.setActionCommand("disconnect");
        disconnect.addActionListener(this);

        settings = new JMenuItem("Settings");
        settings.setActionCommand("settings");
        settings.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(this);

        file.add(disconnect);
        file.add(settings);
        file.add(exit);

        bar.add(file);
        setJMenuBar(bar);
        toggleMenuItems(false);
    }

    private void initLayout() {
        contentPane = new JPanel();
        contentPane.setLayout(cards = new CardLayout());
        contentPane.add(new BrowserCard(core), ConstantConfig.browserCard);
        contentPane.add(new TransferrerCard(this), ConstantConfig.transferrerCard);

        setContentPane(contentPane);
    }

    private void toggleMenuItems(boolean on) {
        disconnect.setEnabled(on);
        settings.setEnabled(!on);
    }

    @Override
    public void dispose() {
        core.stop();
        if(sf != null && sf.isVisible()) {
            sf.dispose();
        }
        Config.getInstance().setWindowLocation(getLocation());
        Config.getInstance().setWindowSize(getSize());
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "disconnect":
                disconnect();
                break;
            case "exit":
                dispose();
                break;
            case "settings":
                settings();
                break;
        }
    }

    private void settings() {
        if (sf == null || !sf.isVisible()) {
            sf = new SettingsFrame(core, getSize(), getLocation());
            sf.setVisible(true);
        }
    }
}
