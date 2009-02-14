package net.javaisp.intellij.plugin.jsonformatter.gui;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;
import net.javaisp.intellij.plugin.jsonformatter.JsonFormatterProjectComponent;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public JsonFormatPanelData() {
        formatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Read the textarea
                    final JSONParser parser = new JSONParser(new StringReader(textArea.getText()));
                    // Parse the first object in the file.
                    JSONValue jsonValue = parser.nextValue();

                    textArea.setText(jsonValue.render(true));
                }
                catch (TokenStreamException e1) {
                    e1.printStackTrace();
                } catch (RecognitionException e1) {
                    e1.printStackTrace();
                }
            }
        });
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

}
