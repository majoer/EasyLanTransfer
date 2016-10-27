package easylantransfer.instance;

import Entity.CustomTable;
import Entity.User;
import easylantransfer.Core.ConstantConfig;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public abstract class TableSingleton implements FocusListener {

    protected DefaultTableModel defaultTableModel;
    protected CustomTable table;
    private int lastSelected = -1;

    protected TableSingleton() {
        defaultTableModel = new DefaultTableModel();
        table = new CustomTable(defaultTableModel);
        table.addFocusListener(this);
    }

    public void addColumn(String name) {
        defaultTableModel.addColumn(name);
    }

    public void clearSelection() {
        table.getSelectionModel().clearSelection();
    }

    public void clear() {
        int numFiles = defaultTableModel.getRowCount();
        for (int i = 0; i < numFiles; i++) {
            defaultTableModel.removeRow(0);
        }
    }

    public void remove(int index) {
        defaultTableModel.removeRow(index);
    }

    public JScrollPane newScrollPane() {
        JScrollPane jsp = new JScrollPane(table);

        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return jsp;
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        lastSelected = -1;
        table.getSelectionModel().clearSelection();
    }

    public JTable getTable() {
        return table;
    }

    public int getLastSelected() {
        return lastSelected;
    }

    public void setLastSelected(int lastSelected) {
        this.lastSelected = lastSelected;
    }
}
