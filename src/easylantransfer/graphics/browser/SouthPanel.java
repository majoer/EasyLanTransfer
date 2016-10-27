package easylantransfer.graphics.browser;

import easylantransfer.instance.UserList;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SouthPanel extends JPanel implements ListSelectionListener {

    private NorthPanel westPanel;

    public SouthPanel(NorthPanel westPanel) {
        this.westPanel = westPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Detected local users"), c);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridy = 1;
        
        JTable table = UserList.getInstance().getTable();
        table.getSelectionModel().addListSelectionListener(this);
        JScrollPane sp = new JScrollPane(table);

        add(sp, c);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        UserList ul = UserList.getInstance();
        
        JTable table = ul.getTable();
        int selected = table.getSelectedRow();
        if (ul.getLastSelected() != selected) {
            westPanel.refreshIP((String) table.getValueAt(selected, 0),
                    (int) table.getValueAt(selected, 2));
        }
        ul.setLastSelected(selected);
    }
}
