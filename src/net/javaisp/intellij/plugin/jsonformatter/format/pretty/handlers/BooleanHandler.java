package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONBoolean;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONBoolean} JSON values (true/false).
 * 
 * @author Cristian Vasile Mocanu
 */
public class BooleanHandler implements PrettyJsonFormatterHandler<JSONBoolean> {
    public void format(StringBuilder result, JSONBoolean jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        result.append(
                String.valueOf(jsonValue.getValue())
        );
    }
}
