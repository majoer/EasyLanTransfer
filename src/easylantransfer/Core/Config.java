package easylantransfer.Core;

import Entity.User;
import java.awt.Dimension;
import java.awt.Point;
import java.util.prefs.Preferences;
import util.Randomizer;

public class Config {

    private static Config instance;

    private final User user;
    private int broadcastRate, broadcastTimeoutRate, UDP_DETECTION_PORT,
            UDP_SENDER_PORT, TCP_LISTEN_PORT, TCP_CONNECT_PORT;
    private Dimension windowSize;
    private Point windowLocation;

    private Config() {
        Preferences node = Preferences.userRoot().node(ConstantConfig.PREFS_NODE);
        String username = node.get(ConstantConfig.PREFS_USERNAME, null);
        if (username == null) {
            username = "User_" + Randomizer.nextUserTag();
        }
        user = new User(username);
        broadcastRate = node.getInt(ConstantConfig.PREFS_BROADCAST_RATE, 1000);
        broadcastTimeoutRate = node.getInt(ConstantConfig.PREFS_BROADCAST_TIMEOUT_RATE, 2);
        UDP_DETECTION_PORT = node.getInt(ConstantConfig.PREFS_UDP_DETECTION_PORT, 50000);
        UDP_SENDER_PORT = node.getInt(ConstantConfig.PREFS_UDP_SENDER_PORT, 50000);
        TCP_LISTEN_PORT = node.getInt(ConstantConfig.PREFS_TCP_LISTEN_PORT, 50000);
        TCP_CONNECT_PORT = node.getInt(ConstantConfig.PREFS_TCP_CONNECT_PORT, 50000);
        windowSize = new Dimension(node.getInt(ConstantConfig.PREFS_WINDOW_SIZE_X, 400),
                node.getInt(ConstantConfig.PREFS_WINDOW_SIZE_Y, 400));
        windowLocation = new Point(node.getInt(ConstantConfig.PREFS_WINDOW_POS_X, 0),
                node.getInt(ConstantConfig.PREFS_WINDOW_POS_Y, 0));
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    private void storeString(String key, String value) {
        Preferences node = Preferences.userRoot().node(ConstantConfig.PREFS_NODE);
        node.put(key, value);
    }
    
    private void storeInt(String key, int value) {
        Preferences node = Preferences.userRoot().node(ConstantConfig.PREFS_NODE);
        node.putInt(key, value);
    }

    public void setBroadcastRate(int broadcastRate) {
        this.broadcastRate = broadcastRate;
        storeInt(ConstantConfig.PREFS_BROADCAST_RATE, broadcastRate);
    }

    public void setBroadcastTimeoutRate(int broadcastTimeoutRate) {
        this.broadcastTimeoutRate = broadcastTimeoutRate;
        storeInt(ConstantConfig.PREFS_BROADCAST_TIMEOUT_RATE, broadcastTimeoutRate);
    }

    public void setUDP_DETECTION_PORT(int UDP_DETECTION_PORT) {
        this.UDP_DETECTION_PORT = UDP_DETECTION_PORT;
        storeInt(ConstantConfig.PREFS_UDP_DETECTION_PORT, UDP_DETECTION_PORT);
    }

    public void setUDP_SENDER_PORT(int UDP_SENDER_PORT) {
        this.UDP_SENDER_PORT = UDP_SENDER_PORT;
        storeInt(ConstantConfig.PREFS_UDP_SENDER_PORT, UDP_SENDER_PORT);
    }

    public void setTCP_LISTEN_PORT(int TCP_LISTEN_PORT) {
        this.TCP_LISTEN_PORT = TCP_LISTEN_PORT;
        storeInt(ConstantConfig.PREFS_TCP_LISTEN_PORT, TCP_LISTEN_PORT);
    }

    public void setTCP_CONNECT_PORT(int TCP_CONNECT_PORT) {
        this.TCP_CONNECT_PORT = TCP_CONNECT_PORT;
        storeInt(ConstantConfig.PREFS_TCP_CONNECT_PORT, TCP_CONNECT_PORT);
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
        storeInt(ConstantConfig.PREFS_WINDOW_SIZE_X, windowSize.width);
        storeInt(ConstantConfig.PREFS_WINDOW_SIZE_Y, windowSize.height);
    }

    public void setWindowLocation(Point windowLocation) {
        this.windowLocation = windowLocation;
        storeInt(ConstantConfig.PREFS_WINDOW_POS_X, windowLocation.x);
        storeInt(ConstantConfig.PREFS_WINDOW_POS_Y, windowLocation.y);
    }

    public void setUsername(String username) {
        storeString(ConstantConfig.PREFS_USERNAME, username);
    }

    public User getUser() {
        return user;
    }

    public int getBroadcastRate() {
        return broadcastRate;
    }

    public int getBroadcastTimeoutRate() {
        return broadcastTimeoutRate;
    }

    public int getUDP_DETECTION_PORT() {
        return UDP_DETECTION_PORT;
    }

    public int getUDP_SENDER_PORT() {
        return UDP_SENDER_PORT;
    }

    public int getTCP_LISTEN_PORT() {
        return TCP_LISTEN_PORT;
    }

    public int getTCP_CONNECT_PORT() {
        return TCP_CONNECT_PORT;
    }

    public Dimension getWindowSize() {
        return windowSize;
    }

    public Point getWindowLocation() {
        return windowLocation;
    }

}
