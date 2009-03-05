package net.javaisp.intellij.plugin.jsonformatter.format;

import net.javaisp.intellij.plugin.jsonformatter.format.compact.CompactJsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.loose.LooseJsonFormatter;
import net.javaisp.intellij.plugin.jsonformatter.format.oneliner.OneLineJsonFormatter;

public final class JsonFormatterFactory {
    private static final JsonFormatter ONE_LINE_FORMATTER = new OneLineJsonFormatter();
    private static final JsonFormatter LOOSE_FORMATTER = new LooseJsonFormatter();

    private JsonFormatterFactory() {
    }

    public static JsonFormatter createFormatter(FormatterType formatterType, int indentSize) {
        switch (formatterType) {
            case ONE_LINE:
                return ONE_LINE_FORMATTER;
            case LOOSE:
                return LOOSE_FORMATTER;
            default:
                return new CompactJsonFormatter(indentSize);
        }
    }
}
