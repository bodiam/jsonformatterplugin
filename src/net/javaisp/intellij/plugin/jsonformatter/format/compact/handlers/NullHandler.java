package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONNull;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONNull} JSON values (null).
 *
 * @author Cristian Vasile Mocanu
 */
public class NullHandler implements CompactJsonFormatterHandler<JSONNull> {
    public void format(StringBuilder result, JSONNull jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        result.append("null");
    }
}
