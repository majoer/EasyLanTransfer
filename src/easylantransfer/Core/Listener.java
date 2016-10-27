package easylantransfer.Core;

import easylantransfer.instance.UserList;
import easylantransfer.graphics.MainGraphics;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener extends MThread {

    private MainGraphics mainGraphics;
    private ServerSocket listener;
    private Connection connection;
    private ArrayList<InetAddress> interfaceAddresses;

    public Listener(MainGraphics mainGraphics, ArrayList<InetAddress> interfaceAddresses) {
        this.mainGraphics = mainGraphics;
        this.interfaceAddresses = interfaceAddresses;
        connection = new Connection(mainGraphics);
    }

    public boolean connect(String ip, int port) {
        if (isSelf(ip)) {
            return false;
        }
        try {
            return connection.connect(ip, port);
        } catch (IOException e) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public void disconnect() {
        connection.stop();
    }

    private boolean isSelf(String testIp) {
        for (InetAddress ip : interfaceAddresses) {
            if (testIp.equals(ip.getHostAddress())) {
                return true;
            }
        }
        return false;
    }

    private void close(Socket client) {
        mainGraphics.disconnect();
        Cleaner.close(client);
    }

    @Override
    public void run() {
        Socket client = null;
        try {
            listener = new ServerSocket(Config.getInstance().getTCP_LISTEN_PORT());

            while (running) {
                System.out.println("accepting connections");
                client = listener.accept();

                if (connection.isConnected()) { 
                    close(client);
                    continue;
                }

                if (!mainGraphics.showDialog(client.getInetAddress())) {
                    Cleaner.close(client);
                    continue;

                }
                if (connection.connect(client)) {
                    System.out.println("Starting connection");
//                    mainGraphics.connect();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.INFO, null, ex);
        } finally {
            Cleaner.close(client);
            Cleaner.close(listener);
        }
    }

    @Override
    public void stop() {
        Cleaner.close(listener);
        super.stop();
    }
}
