package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatterUtils;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONArray} JSON values.
 *
 * @author Cristian Vasile Mocanu
 */
public class ArrayHandler implements PrettyJsonFormatterHandler<JSONArray> {
    public void format(StringBuilder result, JSONArray jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        int elementsCount = jsonValue.size();
        if (elementsCount == 0) {
            result.append("[ ]");
            return;
        }

        result.append("[\n");
        PrettyJsonFormatterUtils.indent(result, currentIndent + formatter.getIndent());

        // todo: don't output newline between elements if the length of the widest element is less that 5 characters; this will make the output even more compact without sacrificing legibility
        for (int i = 0; i < elementsCount - 1; i++) {
            JSONValue element = jsonValue.get(i);

            // format array element
            formatter.format(result, element, currentIndent + formatter.getIndent());
            result.append(",\n");
            PrettyJsonFormatterUtils.indent(result, currentIndent + formatter.getIndent());
        }

        // format last element
        JSONValue lastElement = jsonValue.get(elementsCount - 1);
        formatter.format(result, lastElement, currentIndent + formatter.getIndent());
        result.append("\n");
        PrettyJsonFormatterUtils.indent(result, currentIndent);

        result.append("]");

    }
}
