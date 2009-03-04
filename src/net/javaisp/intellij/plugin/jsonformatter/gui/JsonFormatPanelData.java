package net.javaisp.intellij.plugin.jsonformatter.gui;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamRecognitionException;
import antlr.ANTLRException;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;
import net.javaisp.intellij.plugin.jsonformatter.JsonFormatterProjectComponent;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;
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
    private RTextScrollPane scrollPane;

    public JsonFormatPanelData() {
        formatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    // Read the textarea
                    final JSONParser parser = new JSONParser(new StringReader(textArea.getText()));
                    // Parse the first object in the file.
                    JSONValue jsonValue = parser.nextValue();

                    JsonFormatter formatter = new PrettyJsonFormatter();

                    textArea.setText(
                            formatter.format(jsonValue)
                    );

                    handleInfoMessage("Formatted!");
                } catch (ANTLRException e) {
                    handleErrorMessage("Error:" + e);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("JsonFormatPanelData");
        frame.setContentPane(new JsonFormatPanelData().rootComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        scrollPane = new RTextScrollPane(500, 500, textArea, true);
    }
}
