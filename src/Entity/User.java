package Entity;

import easylantransfer.Core.Config;
import java.net.InetAddress;

public class User {

    private String username;
    private int time;
    private InetAddress inetAddress;
    private int tcpPort;

    public User(String username) {
        this.username = username;
        this.inetAddress = null;
        this.time = 0;
    }

    public User(String username, InetAddress inetAddress) {
        this.username = username;
        this.inetAddress = inetAddress;
        this.time = 0;
    }

    public User(String username, InetAddress inetAddress, int tcpPort) {
        this.username = username;
        this.inetAddress = inetAddress;
        this.time = 0;
        this.tcpPort = tcpPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Config.getInstance().setUsername(username);
        this.username = username;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return username;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }


}
