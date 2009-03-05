package net.javaisp.intellij.plugin.jsonformatter.format.compact;

import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.JsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers.Handlers;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers.CompactJsonFormatterHandler;

/**
 * A compact {@link JsonFormatter}.
 *
 * @author Cristian Vasile Mocanu
 */
public class CompactJsonFormatter implements JsonFormatter {
    private int indent;

    public CompactJsonFormatter() {
        this(4);
    }

    public CompactJsonFormatter(int indent) {
        this.indent = indent;
    }

    public int getIndent() {
        return indent;
    }

    public String format(JSONValue jsonValue) {
        return format(jsonValue, 0);
    }

    private String format(JSONValue jsonValue, int currentIndent) {
        StringBuilder result = new StringBuilder();

        format(result, jsonValue, currentIndent);

        return result.toString();
    }


    @SuppressWarnings({"unchecked"})
    public void format(StringBuilder result, JSONValue jsonValue, int currentIndent) {
        if (jsonValue == null) {
            formatUnknown(result, jsonValue);
            return;
        }

        CompactJsonFormatterHandler handler = Handlers.getHandler(jsonValue.getClass());
        if (handler == null) {
            formatUnknown(result, jsonValue);
        } else {
            handler.format(result, jsonValue, currentIndent, this);
        }
    }

    private static void formatUnknown(StringBuilder result, JSONValue jsonValue) {
        String className = CompactJsonFormatterUtils.safeClassName(jsonValue);

        result.append("ERROR: unknown json value type: [").append(className).append("]");
    }
}
