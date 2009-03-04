package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONNull;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONNull} JSON values (null).
 *
 * @author Cristian Vasile Mocanu
 */
public class NullHandler implements PrettyJsonFormatterHandler<JSONNull> {
    public void format(StringBuilder result, JSONNull jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        result.append("null");
    }
}
