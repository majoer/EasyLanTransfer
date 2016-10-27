package easylantransfer.graphics.transferrer;

import easylantransfer.instance.FileList;
import easylantransfer.instance.TableSingleton;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class FileManagementPanel extends JPanel implements FocusListener {
    private boolean send;

    public FileManagementPanel(boolean send) {
        this.send = send;
        setLayout(new GridLayout(1, 1));

        DragDrop dd = new DragDrop();

        JScrollPane jsp;
        jsp = FileList.getInstance(send).newScrollPane();
        jsp.addFocusListener(this);

        jsp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "removeFile");
        jsp.getActionMap().put("removeFile", createRemoveAction());

        new DropTarget(jsp, dd);

        add(jsp);
    }

    private Action createRemoveAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(send == true) {
                    FileList.getInstance(send).removeSelectedFiles();
                }
            }
        };
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        FileList.getInstance(send).clearSelection();
    }
}
