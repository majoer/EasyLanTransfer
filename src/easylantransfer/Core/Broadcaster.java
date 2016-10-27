package easylantransfer.Core;

import Entity.UDPMessage;
import easylantransfer.instance.UserList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Broadcaster extends MThread {

    private ArrayList<InetAddress> broadcasts;
    private DatagramSocket s = null;

    public Broadcaster(ArrayList<InetAddress> broadcasts) {
        this.broadcasts = broadcasts;

    }

    @Override
    public void run() {
        try {
            s = new DatagramSocket();
            while (running) {
                byte[] send = new UDPMessage(Config.getInstance().getUser().getUsername(),
                        Config.getInstance().getTCP_LISTEN_PORT()).toString().getBytes();
                for (InetAddress broadcast : broadcasts) {

                    DatagramPacket packet = new DatagramPacket(send, send.length, broadcast,
                            Config.getInstance().getUDP_SENDER_PORT());

                    s.send(packet);
                }
                UserList.getInstance().checkTimeout();
                Cleaner.sleep(Config.getInstance().getBroadcastRate());
            }
        } catch (SocketException ex) {
            Logger.getLogger(Broadcaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Broadcaster.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Cleaner.close(s);
        }
    }

    public static void main(String[] args) throws Exception {
//        ArrayList<InetAddress> c = new ArrayList<>();
//        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//        while (interfaces.hasMoreElements()) {
//            NetworkInterface networkInterface = interfaces.nextElement();
//            if (!networkInterface.isLoopback()) {
//                for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
//                    InetAddress bcast = address.getBroadcast();
//                    if (bcast != null) {
//                        c.add(bcast);
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < c.size(); i++) {
//            System.out.println(c.get(i).toString());
//        }
//        Broadcaster b = new Broadcaster(c.get(0), "Frank");
//        b.start();
//
//        Cleaner.sleep(20000);
//        b.stop();
    }
}
