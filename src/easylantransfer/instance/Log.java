package easylantransfer.instance;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Log {
    public static Log instance;
    private JTextArea log;
    private Document document;
    
    private Log() {
        log = new JTextArea();
        document = log.getDocument();
        
        log.setEditable(false);
        
        log.setBackground(Color.black);
        log.setForeground(Color.orange);
        
    }
    
    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }
    
    public JTextArea getLog() {
        return log;
    }
    
    public void append(String text) {
        try {
            document.insertString(document.getLength(), text, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
