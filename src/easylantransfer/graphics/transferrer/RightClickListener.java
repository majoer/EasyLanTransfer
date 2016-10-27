package easylantransfer.graphics.transferrer;

import Entity.Interval;
import easylantransfer.instance.FileList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RightClickListener implements MouseListener, MouseMotionListener {

    private FileList f;
    private int dragStart = 0;
    private boolean rightClickPressed;
    private ListPopupMenu popup;

    public RightClickListener(FileList f) {
        this.f = f;
        popup = new ListPopupMenu();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightClickPressed = true;
            dragStart = f.getTable().rowAtPoint(e.getPoint());
        } else {
            popup.setVisible(false);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {

            if (e.getComponent().contains(e.getPoint())) {
                int selectedRow = f.getTable().rowAtPoint(e.getPoint());
                if (!isIn(selectedRow, f.getLastSelectedRows())) {
                    f.getTable().setRowSelectionInterval(selectedRow, selectedRow);
                }
                rightClickPressed = false;
                dragStart = 0;
                popup(e);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        popup.setVisible(false);
    }

    private void popup(MouseEvent e) {
        popup.setVisible(true);
        popup.setLocation(e.getLocationOnScreen());

    }

    private boolean isIn(int row, int[] rows) {
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == row) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (rightClickPressed) {
            int end = f.getTable().rowAtPoint(e.getPoint());
            if (e.getComponent().contains(e.getPoint())) {
                f.getTable().setRowSelectionInterval(dragStart, end);
            } else {
//                System.out.println("outside");
//                f.getTable().scrollRectToVisible(f.getTable().getCellRect(end + 1, 0, false));
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
