package easylantransfer.Core;

import java.awt.Dimension;
import java.awt.Point;

public class ConstantConfig {
    public static final String[] settings = {"Network", "Disk"};
    public static final String STAMP = "EasyLanTransfer_3432521094411510385",
            browserCard = "browsercard",
            transferrerCard = "transferrercard";
    public static final int maxUsernameLength = 15;
    public static int USER_TAG_NUMBER_LENGTH = 6;
    
    public static final String PREFS_NODE = "EasyLanTransfer",
            PREFS_USERNAME = "username",
            PREFS_BROADCAST_RATE = "broadcastRate",
            PREFS_BROADCAST_TIMEOUT_RATE = "timeoutRate",
            PREFS_UDP_DETECTION_PORT = "UDPDetectionPort",
            PREFS_UDP_SENDER_PORT = "UDPSenderPort",
            PREFS_TCP_LISTEN_PORT = "TCPPort",
            PREFS_TCP_CONNECT_PORT = "TCPSenderPort",
            PREFS_WINDOW_SIZE_X = "windowSizeX",
            PREFS_WINDOW_SIZE_Y = "windowSizeY",
            PREFS_WINDOW_POS_X = "windowPosX",
            PREFS_WINDOW_POS_Y = "WindowPosY";
    public static Dimension SETTINGS_FRAME_SIZE = new Dimension(600, 400);

    public static enum SUPPORTED_CLASSES {

        STRING, ArrayList_File;
    }
}
