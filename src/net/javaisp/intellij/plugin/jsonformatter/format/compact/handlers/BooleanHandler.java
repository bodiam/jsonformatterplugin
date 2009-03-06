package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONBoolean;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONBoolean} JSON values (true/false).
 * 
 * @author Cristian Vasile Mocanu
 */
public class BooleanHandler implements CompactJsonFormatterHandler<JSONBoolean> {
    public void format(StringBuilder result, JSONBoolean jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        result.append(
                String.valueOf(jsonValue.getValue())
        );
    }
}
