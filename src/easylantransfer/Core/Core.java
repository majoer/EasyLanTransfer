package easylantransfer.Core;

import Entity.User;
import easylantransfer.EasyLanTransfer;
import easylantransfer.graphics.MainGraphics;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import util.Randomizer;

public class Core {

    private MainGraphics mainGraphics;
    private BroadcastDetecter broadcastDetecter;
    private Broadcaster broadcaster;
    private Listener listener;
    private boolean running;

    public Core() {

    }

    public void start() {
        try {
            ArrayList<InetAddress> broadcastAddresses = new ArrayList<>();
            ArrayList<InetAddress> interfaceAddresses = new ArrayList<>();

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || (!networkInterface.isUp())) {
                    continue;
                }

                for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
                    InetAddress bcast = address.getBroadcast();
                    if (bcast != null) {
                        broadcastAddresses.add(bcast);
                        interfaceAddresses.add(address.getAddress());
                    }

                }
            }

            for (int i = 0; i < broadcastAddresses.size(); i++) {
                System.out.println("interface: " + interfaceAddresses.get(i).toString());
                System.out.println("Broadcast: " + broadcastAddresses.get(i).toString());
            }

            broadcaster = new Broadcaster(broadcastAddresses);
            broadcaster.start();
            broadcastDetecter = new BroadcastDetecter(interfaceAddresses);
            broadcastDetecter.start();
            listener = new Listener(mainGraphics, interfaceAddresses);
            listener.start();
            running = true;
        } catch (SocketException ex) {
            Logger.getLogger(EasyLanTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean connect(String ip, int port) {
        return listener.connect(ip, port);
    }

    public void stop() {
        broadcastDetecter.stop();
        broadcaster.stop();
        listener.stop();
//        Preferences.userRoot().node(ConstantConfig.PREFS_NODE).put(ConstantConfig.PREFS_USERNAME,
//                Config.getInstance().getUser().getUsername());
        running = false;
    }

    public void disconnect() {
        listener.disconnect();
    }

    public void setMainGraphcs(MainGraphics mainGraphics) {
        this.mainGraphics = mainGraphics;
    }

    public boolean isRunning() {
        return running;
    }

}
