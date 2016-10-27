package easylantransfer.graphics.settings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ConfirmPanel extends JPanel implements ActionListener {
    private SettingsFrame sf;

    public ConfirmPanel(SettingsFrame sf) {
        this.sf = sf;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 3, 3);

        JButton apply = new JButton("Apply");
        JButton cancel = new JButton("Cancel");
        JButton ok = new JButton("Ok");

        apply.setActionCommand("apply");
        cancel.setActionCommand("cancel");
        ok.setActionCommand("ok");

        add(apply, c);

        c.weightx = 0;
        c.gridx = 1;
        add(cancel, c);
        c.gridx = 2;
        add(ok, c);

        apply.addActionListener(this);
        cancel.addActionListener(this);
        ok.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "apply":
                apply();
                break;
            case "cancel":
                cancel();
                break;
            case "ok":
                ok();
                break;
        }
    }

    private void apply() {
        sf.getContentPanel().save();
        sf.restartCore();
    }

    private void cancel() {
        sf.dispose();
    }

    private void ok() {
        apply();
        sf.dispose();
    }
}
