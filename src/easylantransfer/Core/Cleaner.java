package easylantransfer.Core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cleaner {

    public static void close(Socket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void close(ServerSocket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void close(ObjectInputStream s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void close(ObjectOutputStream s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void close(DatagramSocket s) {
        if (s != null) {    
            s.close();
        }
    }
    
    public static void join(Thread t) {
        if(t != null) {
            if(t.isAlive()) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cleaner.class.getName()).log(Level.FINEST, null, ex);
                }
            }
        }
    }
    
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cleaner.class.getName()).log(Level.FINEST, null, ex);
        }
    }
}
