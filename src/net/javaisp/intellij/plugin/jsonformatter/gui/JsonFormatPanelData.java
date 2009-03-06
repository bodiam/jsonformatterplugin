package net.javaisp.intellij.plugin.jsonformatter.gui;

import antlr.ANTLRException;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamRecognitionException;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;
import net.javaisp.intellij.plugin.jsonformatter.JsonFormatterApplicationComponent;
import net.javaisp.intellij.plugin.jsonformatter.JsonFormatterProjectComponent;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatterFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.StringReader;

/**
 * Input form for formatting JSON data.
 *
 * @author Erik Pragt
 */
public class JsonFormatPanelData {

    private JPanel rootComponent;
    private JButton formatButton;
    private RSyntaxTextArea textArea;
    private JLabel message;
    @SuppressWarnings({"UnusedDeclaration"})
    private RTextScrollPane scrollPane;

    public JsonFormatPanelData() {
        $$$setupUI$$$();
        formatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    // Read the textarea
                    final JSONParser parser = new JSONParser(new StringReader(textArea.getText()));
                    // Parse the first object in the file.
                    JSONValue jsonValue = parser.nextValue();

                    JsonFormatter formatter = getFormatter();

                    textArea.setText(
                            formatter.format(jsonValue)
                    );

                    // go at the beginning
                    textArea.setCaretPosition(0);
                    textArea.requestFocus();

                    handleInfoMessage("Formatted!");
                } catch (ANTLRException e) {
                    handleErrorMessage("Error:" + e);
                    putCursorOnError(e);
                }
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                try {
                    validateTextArea();
                    handleInfoMessage("JSON is valid!");
                } catch (Exception e) {
                    handleErrorMessage("Invalid JSON: " + e);
                }
            }
        });
    }

    private void putCursorOnError(ANTLRException exception) {
        int line = 0;
        int column = 0;

        RecognitionException recognitionException = null;

        if (exception instanceof RecognitionException) {
            recognitionException = (RecognitionException) exception;
        } else if (exception instanceof TokenStreamRecognitionException) {
            TokenStreamRecognitionException tokenStreamRecognitionException = (TokenStreamRecognitionException) exception;
            recognitionException = tokenStreamRecognitionException.recog;
        }

        if (recognitionException != null) {
            line = recognitionException.line;
            column = recognitionException.column;
        }

        textAreaGoto(line, column);
    }

    private void textAreaGoto(int line, int column) {
        String text = textArea.getText();
        String[] lines = text.split("\n");
        int pos = 0;
        for (int i = 0; i < line - 1 && i < lines.length; i++) {
            pos += lines[i].length() + 1;
        }
        pos += column - 1;

        if (pos >= 0 && pos <= text.length()) {
            textArea.setCaretPosition(pos);
        }
        textArea.requestFocus();
    }

    private JsonFormatter getFormatter() {
        JsonFormatterApplicationComponent applicationComponent = (JsonFormatterApplicationComponent) ApplicationManager.getApplication().getComponent(JsonFormatterApplicationComponent.APPLICATION_NAME);

        return JsonFormatterFactory.createFormatter(applicationComponent.getFormatterType(), applicationComponent.getIndentSize());
    }

    private void handleInfoMessage(String text) {
        message.setText(text);
    }

    private void handleErrorMessage(String text) {
        Color original = message.getForeground();
        message.setForeground(Color.RED);
        message.setText(text);
        message.setForeground(original);
    }

    private void validateTextArea() throws TokenStreamException, RecognitionException {
        final JSONParser parser = new JSONParser(new StringReader(textArea.getText()));
        parser.nextValue();
    }

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public void setData(JsonFormatterProjectComponent data) {
        textArea.setText(data.getJsonData());
    }

    public void getData(JsonFormatterProjectComponent data) {
        data.setJsonData(textArea.getText());
    }

    public boolean isModified(JsonFormatterProjectComponent data) {
        return textArea.getText() != null ? !textArea.getText().equals(data.getJsonData()) : data.getJsonData() != null;
    }

    private void createUIComponents() {
        scrollPane = new RTextScrollPane(500, 500, textArea, true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootComponent = new JPanel();
        rootComponent.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        formatButton = new JButton();
        formatButton.setText("Format");
        formatButton.setMnemonic('F');
        formatButton.setDisplayedMnemonicIndex(0);
        rootComponent.add(formatButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootComponent.add(spacer1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        message = new JLabel();
        message.setText("");
        rootComponent.add(message, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rootComponent.add(scrollPane, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textArea = new RSyntaxTextArea();
        textArea.setAutoIndentEnabled(true);
        textArea.setAutoscrolls(true);
        textArea.setClearWhitespaceLinesEnabled(true);
        textArea.setEditable(true);
        textArea.setEnabled(true);
        textArea.setHighlightCurrentLine(true);
        textArea.setHyperlinksEnabled(true);
        textArea.setOpaque(true);
        textArea.setRequestFocusEnabled(true);
        textArea.setSyntaxEditingStyle("text/java");
        textArea.setText("");
        textArea.setVerifyInputWhenFocusTarget(true);
        textArea.setVisible(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setViewportView(textArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootComponent;
    }
}
