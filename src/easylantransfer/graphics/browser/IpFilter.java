package easylantransfer.graphics.browser;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IpFilter extends DocumentFilter {

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (test(fb, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (test(fb, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean test(FilterBypass fb, String string) throws BadLocationException {

        if (fb.getDocument().getLength() + string.length() > 3) {
            return false;
        }

        char[] seq = string.toCharArray();
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] < 48 | seq[i] > 57) {
                return false;
            }
        }
        Document d = fb.getDocument();

        int current = Integer.parseInt(d.getText(0, d.getLength()) + string);
        if (current > 255) {
            return false;
        }
        return true;
    }
}
