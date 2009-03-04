package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONString;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatterUtils;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONString} JSON values.
 *
 * @author Cristian Vasile Mocanu
 */
public class StringHandler implements PrettyJsonFormatterHandler<JSONString> {
    public void format(StringBuilder result, JSONString jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        PrettyJsonFormatterUtils.formatString(
                result,
                jsonValue.getValue()
        );
    }
}
