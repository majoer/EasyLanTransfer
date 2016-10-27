package easylantransfer.Core;

import Entity.Message;
import Entity.UDPMessage;
import Entity.exception.MessageFormatException;
import easylantransfer.instance.UserList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BroadcastDetecter extends MThread {

    private DatagramSocket datagramSocket;
    private ArrayList<InetAddress> interfaces;

    public BroadcastDetecter(ArrayList<InetAddress> interfaces) {
        this.interfaces = interfaces;
        datagramSocket = null;
    }

    @Override
    public void run() {
        try {
            datagramSocket = new DatagramSocket(Config.getInstance().getUDP_DETECTION_PORT());
            int len = (ConstantConfig.STAMP + Message.MessageType.HELLO).length()
                    + ConstantConfig.maxUsernameLength + 3 + 5; //+ 3 " and max port number
            byte[] rcv = new byte[len];

            while (running) {
                DatagramPacket p = new DatagramPacket(rcv, len);
                datagramSocket.receive(p);   //Blocks!
                if (!self(p.getAddress())) {
                    handlePacket(p);
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(BroadcastDetecter.class.getName()).log(Level.INFO, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BroadcastDetecter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Cleaner.close(datagramSocket);
        }
    }

    @Override
    public void stop() {
        Cleaner.close(datagramSocket);
        super.stop();
    }

    private boolean self(InetAddress receiver) {
        for (InetAddress iface : interfaces) {
            if (iface.equals(receiver)) {
                return true;
            }
        }
        return false;
    }

    private void handlePacket(DatagramPacket p) {
        try {
            UDPMessage message = new UDPMessage(new String(p.getData(), 0, p.getLength()));
            System.out.println(message);
            UserList.getInstance().populate(p.getAddress(), message);
        } catch (MessageFormatException e) {
            Logger.getLogger(BroadcastDetecter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
//        BroadcastDetecter bd = new BroadcastDetecter();
//        bd.start();
//        Cleaner.sleep(10000);
//        bd.stop();
    }
}
