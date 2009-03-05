package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.JSONDecimal;
import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;

import java.math.BigDecimal;

/**
 * A {@link CompactJsonFormatterHandler} that knows how to format {@link JSONDecimal} JSON values (floating point numbers).
 *
 * @author Cristian Vasile Mocanu
 */
public class DecimalHandler implements CompactJsonFormatterHandler<JSONDecimal> {
    public void format(StringBuilder result, JSONDecimal jsonValue, int currentIndent, CompactJsonFormatter formatter) {
        BigDecimal value = jsonValue.getValue();

        if (value == null) {
            result.append("null");
        } else {
            result.append(value.toPlainString());
        }
    }
}
