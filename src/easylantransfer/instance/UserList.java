package easylantransfer.instance;

import Entity.UDPMessage;
import Entity.User;
import easylantransfer.Core.Config;
import easylantransfer.Core.ConstantConfig;
import java.net.InetAddress;

public class UserList extends TableSingleton {

    private static UserList instance;

    public UserList() {
        super();
        super.addColumn("IP-Address");
        super.addColumn("Username");
        super.addColumn("Port");
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return (UserList) instance;
    }

    public void populate(InetAddress ip, UDPMessage udpMessage) {
        User u;
        if ((u = search(ip)) != null) {
            testUpdate(u, udpMessage, ip);
            u.setTime(0);
        } else {
            u = new User(udpMessage.getUsername(), ip, udpMessage.getTcpPort());
            defaultTableModel.addRow(new Object[]{ip.getHostAddress(),
                u, udpMessage.getTcpPort()});
        }
    }

    public void update(InetAddress inet, User u) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            User target = (User) defaultTableModel.getValueAt(i, 1);
            if (target.getInetAddress().equals(inet)) {
                defaultTableModel.setValueAt(u, i, 1);
                defaultTableModel.setValueAt(u.getTcpPort(), i, 2);
            }
        }
    }

    public void checkTimeout() {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            User target = (User) defaultTableModel.getValueAt(i, 1);
            target.setTime(target.getTime() + Config.getInstance().getBroadcastRate());
            if (target.getTime() > Config.getInstance().getBroadcastRate()
                    * Config.getInstance().getBroadcastTimeoutRate()) {
                defaultTableModel.removeRow(i--);
            }
        }
    }

    public User search(InetAddress inetAddress) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            User target = (User) defaultTableModel.getValueAt(i, 1);
            if (target.getInetAddress().equals(inetAddress)) {
                return target;
            }
        }
        return null;
    }

    private void testUpdate(User u, UDPMessage udpMessage, InetAddress ip) {
        if (u.getTcpPort() != udpMessage.getTcpPort() ||
                !u.getUsername().equals(udpMessage.getUsername())) {
            
            update(ip, new User(udpMessage.getUsername(), ip, udpMessage.getTcpPort()));
        }
    }
}
