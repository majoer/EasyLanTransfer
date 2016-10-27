package Entity;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomTable extends JTable {

    public CustomTable() {
        super();
    }

    public CustomTable(DefaultTableModel dtm) {
        super(dtm);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
