package net.javaisp.intellij.plugin.jsonformatter.format.loose;

import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter;

/**
 * A not-so-compact {@link net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter}.
 *
 * @author Cristian Vasile Mocanu
 */
public class LooseJsonFormatter implements JsonFormatter {
    public String format(JSONValue jsonValue) {
        if (jsonValue == null) {
            return "null";
        }

        boolean pretty = true;

        return jsonValue.render(pretty);
    }
}
