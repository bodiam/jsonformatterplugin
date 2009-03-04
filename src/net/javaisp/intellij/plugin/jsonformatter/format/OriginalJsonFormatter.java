package net.javaisp.intellij.plugin.jsonformatter.format;

import com.sdicons.json.model.JSONValue;

/**
 * A not-so-compact {@link JsonFormatter}.
 *
 * @author Cristian Vasile Mocanu
 */
public class OriginalJsonFormatter implements JsonFormatter {
    public String format(JSONValue jsonValue) {
        if (jsonValue == null) {
            return "null";
        }

        boolean pretty = true;

        return jsonValue.render(pretty);
    }
}
