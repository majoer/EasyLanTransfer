package easylantransfer.instance;

import Entity.FileDescription;
import Entity.Status;
import easylantransfer.Core.Connection;
import Entity.Message;
import easylantransfer.graphics.transferrer.ListPopupMenu;
import easylantransfer.graphics.transferrer.RightClickListener;
import java.awt.Point;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

public class FileList extends TableSingleton implements ListSelectionListener {

    private static FileList sendInstance, receiveInstance;
    private ArrayList<FileDescription> files;
    private Connection connection;
    private int[] lastSelectedRows;
    private boolean send;

    protected FileList(boolean send) {
        super();
        this.send = send;
        files = new ArrayList<>();
        addColumn("Name");
        addColumn("Size");
        addColumn("Status");
        getTable().getSelectionModel().addListSelectionListener(this);
        RightClickListener rcl = new RightClickListener(this);
        getTable().addMouseListener(rcl);
         getTable().addMouseMotionListener(rcl);

    }

    public static FileList getInstance(boolean send) {
        if (send) {
            if (sendInstance == null) {
                sendInstance = new FileList(send);
            }
            return (FileList) sendInstance;
        } else {
            if (receiveInstance == null) {
                receiveInstance = new FileList(send);
            }
            return (FileList) receiveInstance;
        }
    }

    public synchronized void populate(List<FileDescription> files, boolean isRemoteUpdate) {
        List<FileDescription> add = new ArrayList<>();
        for (FileDescription f : files) {
            populate(f, add);
        }
        if (!isRemoteUpdate) {
            if (connection != null) {
                connection.sendMessage(new Message(Message.MessageType.UPDATE, true, add));
            }
        } else {
            FileManagementPane.getInstance().updateIcon(add.size());
        }
    }

    private synchronized void populate(FileDescription file, List<FileDescription> add) {
        if (file.isDirectory()) {
            for (FileDescription f : file.getChildren()) {
                populate(f, add);
            }
        } else {
            if (search(file) == -1) {
                defaultTableModel.addRow(new Object[]{file.getName(), formatLength(file.getSize()), Status.UNSENT});
                files.add(file);
                if (add != null) {
                    add.add(file);
                }
            }
        }
    }

    public synchronized void removeSelectedFiles() {
        List<FileDescription> remove = new ArrayList();
        int[] rows = table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            int index = rows[i] - i;
            remove.add(files.get(index));
            remove(index);
        }

        if (connection != null) {
            connection.sendMessage(new Message(Message.MessageType.UPDATE, false, remove));
        }
    }

//    public synchronized void highlightRows(int start, int end) {
//        if (start < 0 || end > table.getRowCount()) {
//            return;
//        }
//        for (int col = 0; col < table.getColumnCount(); col++) {
//            for (int row = start; row < end; row++) {
//                table.getCellRenderer(row, col).;
//            }
//        }
//    }
    private synchronized int search(FileDescription f) {
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).equals(f)) {
                return i;
            }
        }
        return -1;
    }

    private String formatLength(long length) {
        StringBuilder sb = new StringBuilder(" ");
        DecimalFormat df = new DecimalFormat("#.##");

        double kb = 1024;
        double mb = kb * 1024;
        double gb = mb * 1024;

        if (length < kb) {
            sb.append(length);
            sb.append("B");
        } else if (length < mb) {
            sb.append(df.format(length / kb));
            sb.append("KB");
        } else if (length < gb) {
            sb.append(df.format(length / mb));
            sb.append("MB");
        } else {
            sb.append(df.format(length / gb));
            sb.append("GB");
        }

        return sb.toString();
    }

    public void bind(Connection connection) {
        if (this.connection == null) {
            this.connection = connection;
        }
    }

    public void unbind() {
        this.connection = null;
    }

    public synchronized void removeUpdate(List<FileDescription> update) {
        int removed = 0;
        for (FileDescription f : update) {
            int found = search(f);
            if (found != -1) {
                remove(found);
                removed++;
            }
        }
        FileManagementPane.getInstance().updateIcon(removed);
    }

    public Status[] getSelectedStatuses() {
        int[] sel = getTable().getSelectedRows();
        Status[] statuses = new Status[sel.length];
        int index = 0;
        for (int i : sel) {
            statuses[index++] = files.get(i).getStatus();
        }
        return statuses;

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selected = getTable().getSelectedRow();
        if (getLastSelected() != selected) {
            FileInfo.getInstance().updateFileInfo(files.get(selected));
            lastSelectedRows = table.getSelectedRows();
        }
    }

    @Override
    public synchronized void clear() {
        super.clear();
        files.clear();
    }

    @Override
    public synchronized void remove(int index) {
        super.remove(index);
        files.remove(index);
    }

    public int[] getLastSelectedRows() {
        return lastSelectedRows;
    }

    public boolean isSend() {
        return send;
    }

}
