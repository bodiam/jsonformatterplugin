package net.javaisp.intellij.plugin.jsonformatter;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import net.javaisp.intellij.plugin.jsonformatter.gui.JsonFormatPanelData;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Main component to load the icon and tool window.
 * 
 * @author Erik Pragt
 */
public class JsonFormatterProjectComponent implements ProjectComponent {
    private String jsonData;
    private Project project;

    public static final String TOOL_WINDOW_ID = "JSON Formatter";

    public JsonFormatterProjectComponent(Project project) {
        this.project = project;
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "JsonFormatterProjectComponent";
    }

    public void projectOpened() {
        initToolWindow();
    }

    public void projectClosed() {
        unregisterToolWindow();
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }


    private void initToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        JComponent panel = new JsonFormatPanelData().getRootComponent();

        ToolWindow toolWindow = toolWindowManager.registerToolWindow(TOOL_WINDOW_ID, false, ToolWindowAnchor.BOTTOM);
        toolWindow.setIcon(new ImageIcon(this.getClass().getResource("wand.png")));

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void unregisterToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        toolWindowManager.unregisterToolWindow(TOOL_WINDOW_ID);
    }
}
