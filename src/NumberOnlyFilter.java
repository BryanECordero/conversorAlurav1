import javax.swing.text.*;

public class NumberOnlyFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
        // Permite solo caracteres numéricos o el signo "-" en la posición inicial
        if (isNumber(text) || canInsertNegativeSign(fb, offset, text)) {
            super.insertString(fb, offset, text, attrs);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Permite solo caracteres numéricos o el signo "-" en la posición inicial
        if (isNumber(text) || canReplaceNegativeSign(fb, offset, length, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumber(String text) {
        return text.matches("-?\\d*\\.?\\d*"); // Verifica si el texto contiene solo dígitos
    }

    private boolean canInsertNegativeSign(FilterBypass fb, int offset, String text) throws BadLocationException {
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());
        return offset == 0 && text.equals("-") && !currentText.contains("-");
    }

    private boolean canReplaceNegativeSign(FilterBypass fb, int offset, int length, String text) throws BadLocationException {
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());
        return length == 1 && offset == 0 && text.equals("-") && !currentText.contains("-") && !text.equals(currentText) && !isNumber(currentText + text);
    }
}
