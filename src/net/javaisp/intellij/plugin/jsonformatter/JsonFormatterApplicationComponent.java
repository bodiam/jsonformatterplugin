package net.javaisp.intellij.plugin.jsonformatter;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import net.javaisp.intellij.plugin.jsonformatter.gui.JsonFormetterConfiguration;
import net.javaisp.intellij.plugin.jsonformatter.format.FormatterType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * The application component is only to hold the configuration data at the application level, because we don't want/need
 * to have different configuration per project.
 */
@State(
  name="JsonFormatterApplicationSettings",
  storages = {
    @Storage(
      id="JsonFormatterApplicationSettings",
      file="$APP_CONFIG$/json-formatter.xml"
    )}
)
public class JsonFormatterApplicationComponent implements ApplicationComponent, Configurable, PersistentStateComponent<JsonFormatterApplicationComponent.ConfigurationBean> {
    public static final String APPLICATION_NAME = "JsonFormatterApplicationComponent";

    private JsonFormetterConfiguration configurationPanel;

    private ConfigurationBean configurationBean;
    private int indentSize = 4;
    private FormatterType formatterType;

    public static class ConfigurationBean {
        public int indentSize = 4;
        public FormatterType formatterType;
    }

    public JsonFormatterApplicationComponent() {
        configurationBean = new ConfigurationBean();
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return APPLICATION_NAME;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public void setIndentSize(final int indentSize) {
        this.indentSize = indentSize;
    }

    public FormatterType getFormatterType() {
        return formatterType;
    }

    public void setFormatterType(FormatterType formatterType) {
        this.formatterType = formatterType;
    }

    @Nls
    public String getDisplayName() {
        return "Json Formatter";
    }

    public Icon getIcon() {
        return new ImageIcon(
                getClass().getResource("net/javaisp/intellij/plugin/jsonformatter/wand.png")
        );
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (configurationPanel == null) {
            configurationPanel = new JsonFormetterConfiguration();
        }

        return configurationPanel.getRootComponent();
    }

    public boolean isModified() {
        return configurationPanel != null && configurationPanel.isModified(this);
    }

    public void apply() throws ConfigurationException {
        // Get data from form to component
        if (configurationPanel != null) {
            configurationPanel.getData(this);
        }
    }

    public void reset() {
        // Reset form data from component
        if (configurationPanel != null) {
            configurationPanel.setData(this);
        }
    }

    public void disposeUIResources() {
        configurationPanel = null;
    }

    public ConfigurationBean getState() {
        configurationBean.indentSize = indentSize;
        configurationBean.formatterType = formatterType;

        return configurationBean;
    }

    public void loadState(ConfigurationBean state) {
        indentSize = state.indentSize;
        formatterType = state.formatterType;
    }
}
