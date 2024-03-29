package com.chainarong.chessgame;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class NumericFilter extends DocumentFilter {
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (isNumeric(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (isNumeric(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}