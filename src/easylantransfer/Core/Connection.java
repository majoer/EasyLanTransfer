package easylantransfer.Core;

import Entity.Message;
import Entity.FileDescription;
import Entity.exception.MessageException;
import Entity.User;
import easylantransfer.instance.ConnectedRemoteUser;
import easylantransfer.instance.FileList;
import easylantransfer.instance.UserList;
import easylantransfer.graphics.MainGraphics;
import easylantransfer.instance.FileManagementPane;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection extends MThread {

    private MainGraphics mainGraphics;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Connection(MainGraphics mainGraphics) {
        this.mainGraphics = mainGraphics;
        oos = null;
        ois = null;
    }

    /**
     * Initiating connect call
     *
     * @param socket
     * @return
     */
    public synchronized boolean connect(Socket socket) {
        if (isConnected()) {
            return false;
        }

        this.socket = socket;
        sendMessage(new Message(Message.MessageType.HELLO, Config.getInstance().getUser().getUsername()));
        start();
        return true;
    }

    /**
     * Listening connect call
     *
     * @param address
     * @param port
     * @return
     * @throws IOException
     */
    public synchronized boolean connect(String address, int port) throws IOException {
        if (isConnected()) {
            return false;
        }

        socket = new Socket(address, port);
        start();
        return true;
    }

    public synchronized void sendMessage(Message message) {
        try {
            if (oos == null) {
                oos = new ObjectOutputStream(socket.getOutputStream());
            }
            oos.writeObject(message);
            oos.reset();
            System.out.println("Sent Message: " + message.getMessageType());
        } catch (IOException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public synchronized boolean isConnected() {
        if (socket != null) {
            return socket.isConnected();
        }
        return false;
    }

    @Override
    public void run() {
        FileList.getInstance(true).bind(this);
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            while (running) {
                System.out.println("Awaiting new messages");
                Message m = (Message) ois.readObject();
                System.out.println("Got message:" + m.getMessageType());

                switch (m.getMessageType()) {
                    case HELLO:
                        hello(m);
                        break;
                    case USERNAME:
                        username(m);
                        break;
                    case UPDATE:
                        update(m);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.INFO, null, ex);
        } finally {
            reset();
            mainGraphics.disconnect();
        }
    }

    @Override
    public void stop() {
        reset();
        super.stop();
    }

    private void reset() {
        Cleaner.close(oos);
        Cleaner.close(ois);
        Cleaner.close(socket);
        socket = null;
        oos = null;
        ois = null;
    }

    private void hello(Message m) throws MessageException, ClassCastException {
        if (!m.isValid(1)) {
            throw new MessageException();
        }
        User u = new User((String) m.getPayload()[0], socket.getInetAddress());
        ConnectedRemoteUser.getInstance().update(u);
        mainGraphics.connect(u);
        sendMessage(new Message(Message.MessageType.USERNAME, 
                Config.getInstance().getUser().getUsername()));
    }

    private void username(Message m) throws MessageException, ClassCastException {
        if (!m.isValid(1)) {
            throw new MessageException();
        }
        User u = new User((String) m.getPayload()[0], socket.getInetAddress());
        ConnectedRemoteUser.getInstance().update(u);
        mainGraphics.connect(u);
    }

    private void update(Message m) throws MessageException, ClassCastException {
        if (!m.isValid(2)) {
            throw new MessageException();
        }
        if ((boolean) m.getPayload()[0]) {
            FileList.getInstance(false).populate((List<FileDescription>) m.getPayload()[1], true);
        } else {
            FileList.getInstance(false).removeUpdate((List<FileDescription>) m.getPayload()[1]);
        }
    }

}
