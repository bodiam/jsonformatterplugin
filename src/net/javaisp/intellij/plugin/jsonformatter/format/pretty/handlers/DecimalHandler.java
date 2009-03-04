package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONDecimal;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

import java.math.BigDecimal;

/**
 * A {@link PrettyJsonFormatterHandler} that knows how to format {@link JSONDecimal} JSON values (floating point numbers).
 *
 * @author Cristian Vasile Mocanu
 */
public class DecimalHandler implements PrettyJsonFormatterHandler<JSONDecimal> {
    public void format(StringBuilder result, JSONDecimal jsonValue, int currentIndent, PrettyJsonFormatter formatter) {
        BigDecimal value = jsonValue.getValue();

        if (value == null) {
            result.append("null");
        } else {
            result.append(value.toPlainString());
        }
    }
}
