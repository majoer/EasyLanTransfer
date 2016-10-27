package easylantransfer.graphics.transferrer;

import Entity.FileDescription;
import Entity.Status;
import easylantransfer.instance.FileList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class ListPopupMenu extends JPopupMenu {

    private JMenuItem download, remove, stop, pause;

    public ListPopupMenu() {
        init();
        add(download);
        add(remove);
        add(stop);
        add(pause);  
    }
    
    public void update(Status[] fileStatus) {
        for (Status s : fileStatus) {
            switch (s) {
                case DOWNLOADING:
                    stop.setEnabled(true);
                    pause.setEnabled(true);
                    break;

                case UNSENT:
                    download.setEnabled(true);
                    break;
                
            }
        }
    }

    private void init() {
        download = new JMenuItem("Download");
        download.setEnabled(false);

        remove = new JMenuItem("Remove");

        stop = new JMenuItem("Stop");
        stop.setEnabled(false);

        pause = new JMenuItem("Pause");
        pause.setEnabled(false);
    }

}
