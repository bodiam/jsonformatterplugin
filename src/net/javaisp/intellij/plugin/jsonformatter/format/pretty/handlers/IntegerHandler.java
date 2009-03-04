package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONInteger;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONInteger} JSON values (integer numbers).
 *
 * @author Cristian Vasile Mocanu
 */
public class IntegerHandler implements PrettyJsonFormatterHandler<JSONInteger> {
    public void format(StringBuilder result, JSONInteger jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        result.append(
                String.valueOf(jsonValue.getValue())
        );
    }
}
