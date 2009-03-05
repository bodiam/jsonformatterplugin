package net.javaisp.intellij.plugin.jsonformatter.format.oneliner;

import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter;

/**
 * A very compact {@link net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter} that output everything on one line.
 * Useful to minimize the size of the JSON text, but not easy to read.
 *
 * @author Cristian Vasile Mocanu
 */
public class OneLineJsonFormatter implements JsonFormatter {
    public String format(JSONValue jsonValue) {
        if (jsonValue == null) {
            return "null";
        }

        boolean pretty = false;

        return jsonValue.render(pretty);
    }
}