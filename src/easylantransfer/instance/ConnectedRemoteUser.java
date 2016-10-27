package easylantransfer.instance;

import Entity.User;
import javax.swing.JLabel;

public class ConnectedRemoteUser extends JLabel {

    private static ConnectedRemoteUser instance;
    private User remoteUser;

    private ConnectedRemoteUser() {
        super("Connected to: ");
    }

    public static ConnectedRemoteUser getInstance() {
        if (instance == null) {
            instance = new ConnectedRemoteUser();
        }
        return instance;
    }
    
    public void update(User u) {
        if(u != null) {
            remoteUser = u;
            setText("Connected to: " + u.getUsername());
        }
    }
}
