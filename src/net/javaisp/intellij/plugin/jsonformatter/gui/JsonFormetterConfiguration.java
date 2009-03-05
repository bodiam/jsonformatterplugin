package net.javaisp.intellij.plugin.jsonformatter.gui;

import net.javaisp.intellij.plugin.jsonformatter.JsonFormatterApplicationComponent;
import net.javaisp.intellij.plugin.jsonformatter.format.FormatterType;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The configuration dialog that allows the user to choose the formatting style and options.
 */
public class JsonFormetterConfiguration {
    private JRadioButton oneLineFormatterRadioButton;
    private JRadioButton compactFormatterRadioButton;
    private JRadioButton looseFormatterRadioButton;
    private JTextField indentSizeTextField;
    private JPanel rootComponent;

    public JsonFormetterConfiguration() {
        ActionListener formatterTypeRadioButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JRadioButton source = (JRadioButton) event.getSource();

                indentSizeTextField.setEnabled(source == compactFormatterRadioButton);
            }
        };

        oneLineFormatterRadioButton.addActionListener(formatterTypeRadioButtonListener);
        compactFormatterRadioButton.addActionListener(formatterTypeRadioButtonListener);
        looseFormatterRadioButton.addActionListener(formatterTypeRadioButtonListener);
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public void setData(JsonFormatterApplicationComponent data) {
        indentSizeTextField.setText(
                String.valueOf(data.getIndentSize())
        );
        setFormatterType(data.getFormatterType());
    }

    public void getData(JsonFormatterApplicationComponent data) {
        String text = indentSizeTextField.getText();

        int indentSize;
        try {
            indentSize = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            indentSize = 4;
        }

        data.setIndentSize(indentSize);

        data.setFormatterType(getFormatterType());
    }

    private FormatterType getFormatterType() {
        if (oneLineFormatterRadioButton.isSelected()) {
            return FormatterType.ONE_LINE;
        } else if (looseFormatterRadioButton.isSelected()) {
            return FormatterType.LOOSE;
        } else {
            return FormatterType.COMPACT;
        }
    }

    private void setFormatterType(FormatterType formatterType) {
        if (formatterType == FormatterType.ONE_LINE) {
            oneLineFormatterRadioButton.setSelected(true);
        } else if (formatterType == FormatterType.LOOSE) {
            looseFormatterRadioButton.setSelected(true);
        } else {
            compactFormatterRadioButton.setSelected(true);
        }

        indentSizeTextField.setEnabled(compactFormatterRadioButton.isSelected());
    }

    public boolean isModified(JsonFormatterApplicationComponent data) {
        String text = indentSizeTextField.getText();

        if (text != null) {
            if (!text.equals(String.valueOf(data.getIndentSize()))) {
                return true;
            }
        }

        //noinspection RedundantIfStatement
        if (getFormatterType() != data.getFormatterType()) {
            return true;
        }

        return false;
    }

}
