package Entity;

public enum Status {
    DOWNLOADING, UPLOADING, STOPPED, PAUSED, UNSENT, SENT;

    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(0, 1).concat(s.substring(1).toLowerCase());
    }
    
    
}
