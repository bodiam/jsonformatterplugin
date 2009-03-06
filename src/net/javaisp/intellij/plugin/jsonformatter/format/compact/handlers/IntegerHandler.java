package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONInteger;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONInteger} JSON values (integer numbers).
 *
 * @author Cristian Vasile Mocanu
 */
public class IntegerHandler implements CompactJsonFormatterHandler<JSONInteger> {
    public void format(StringBuilder result, JSONInteger jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        result.append(
                String.valueOf(jsonValue.getValue())
        );
    }
}
