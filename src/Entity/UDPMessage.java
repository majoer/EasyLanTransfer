package Entity;

import Entity.exception.MessageFormatException;
import easylantransfer.Core.ConstantConfig;

public class UDPMessage {

    private final int[] messageSplitLengths = {4};
    private String username;
    private int tcpPort;

    public UDPMessage() {
    }

    public UDPMessage(String username, int tcpPort) {
        this.username = username;
        this.tcpPort = tcpPort;
    }

    public UDPMessage(String fromString) throws MessageFormatException {
        System.out.println(fromString);
        String[] split = fromString.split(":");
        if (!supportedLength(split.length)) {
            throw new MessageFormatException("Unexpected message length");
        }
        if (!split[0].equals(ConstantConfig.STAMP)) {
            throw new MessageFormatException("Stamp mismatch");
        }

        if (split[1].equals(Message.MessageType.HELLO.toString())) {
            helloConstruct(split);
        } else {
            throw new MessageFormatException("Unexpected message header");
        }

    }

    private void helloConstruct(String[] split) throws MessageFormatException {
        try {
            tcpPort = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            throw new MessageFormatException("Unreadable port number");
        }
        username = split[3];
    }

    private boolean supportedLength(int len) {
        for (int i : messageSplitLengths) {
            if (len == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantConfig.STAMP).append(":");
        sb.append(Message.MessageType.HELLO).append(":");
        sb.append(tcpPort).append(":");
        sb.append(username);
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

}
