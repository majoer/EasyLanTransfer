package easylantransfer.Core;

public abstract class MThread implements Runnable {
    private Thread thread;
    protected boolean running;
    
    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public void stop() {
        if(thread != null) {
            running = false;
            thread.interrupt();
            Cleaner.join(thread);
        }
    }
}
