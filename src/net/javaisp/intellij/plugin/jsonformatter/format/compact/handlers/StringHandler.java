package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONString;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatterUtils;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONString} JSON values.
 *
 * @author Cristian Vasile Mocanu
 */
public class StringHandler implements CompactJsonFormatterHandler<JSONString> {
    public void format(StringBuilder result, JSONString jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        CompactJsonFormatterUtils.formatString(
                result,
                jsonValue.getValue()
        );
    }
}
