package net.javaisp.intellij.plugin.jsonformatter.format;

import com.sdicons.json.model.JSONValue;

/**
 * Responsible for producing a {@code String} representation for a {@link JSONValue}.
 *
 * @author Cristian Vasile Mocanu
 */
public interface JsonFormatter {
    String format(JSONValue jsonValue);
}
