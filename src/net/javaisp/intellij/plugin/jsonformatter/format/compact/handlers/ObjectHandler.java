package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONObject;
import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatterUtils;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONObject} JSON values.
 *
 * @author Cristian Vasile Mocanu
 */
public class ObjectHandler implements CompactJsonFormatterHandler<JSONObject> {
    public void format(StringBuilder result, JSONObject jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        // todo: cleanup the code a little to make it easier to read
        HashMap<String,JSONValue> map = jsonValue.getValue();
        Set<Map.Entry<String,JSONValue>> entries = map.entrySet();
        int elementsCount = entries.size();

        if (elementsCount == 0) {
            result.append("{ }");

            return;
        }

        result.append("{\n");
        CompactJsonFormatterUtils.indent(result, currentIndent + formatter.getIndent());

        int i=0;

        int largestKeyLength = getLargestKeyLength(map);

        // todo: only align properties if current property has a similar type with the previous or the next property
        for (Map.Entry<String, JSONValue> entry : entries) {
            String key = entry.getKey();
            JSONValue value = entry.getValue();

            CompactJsonFormatterUtils.formatString(result, key);

            // only align primitive types at :
            if (CompactJsonFormatterUtils.isPrimitiveJsonType(value)) {
                CompactJsonFormatterUtils.indent(result, largestKeyLength - key.length());
            }
            result.append(" : ");
            formatter.format(result, value, currentIndent + formatter.getIndent());

            boolean lastElement = (i == elementsCount - 1);
            if (!lastElement) {
                result.append(",");
            }
            result.append("\n");

            if (lastElement) {
                CompactJsonFormatterUtils.indent(result, currentIndent);
            } else {
                CompactJsonFormatterUtils.indent(result, currentIndent + formatter.getIndent());
            }

            i++;
        }

        result.append("}");
    }

    /**
     * @param map the properties of a {@link JSONObject}
     *
     * @return the size of the biggest property name
     */
    private int getLargestKeyLength(HashMap<String, JSONValue> map) {
        int max = 0;

        for (String key : map.keySet()) {
            int length = key.length();

            if (length > max) {
                max = length;
            }
        }

        return max;
    }
}
