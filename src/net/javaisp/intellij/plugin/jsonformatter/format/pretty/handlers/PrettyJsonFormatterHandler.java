package net.javaisp.intellij.plugin.jsonformatter.format.pretty.handlers;

import com.sdicons.json.model.JSONValue;
import net.javaisp.intellij.plugin.jsonformatter.format.pretty.PrettyJsonFormatter;

/**
 * Responsible for formatting a JSON value.
 *
 * @param <T> a subclass of {@link JSONValue} that this handler knows how to format
 *
 * @author Cristian Vasile Mocanu
 */
public interface PrettyJsonFormatterHandler<T extends JSONValue> {

    /**
     * Formats the JSON value indicated by this class' parameter.
     *
     * @param result        this is where the result of this method should be placed. This is a collecting parameter.
     * @param jsonValue     the {@code JSONValue} to format
     * @param currentIndent how many spaces should be prepended to each line added by this handler to the result
     * @param formatter     the {@code PrettyJsonFormatter} needed to lookup informations like the indent step
     *                      (4 spaces by default) and to format sub-elements of {@code jsonValue} if necessary.
     */
    void format(StringBuilder result, T jsonValue, int currentIndent, PrettyJsonFormatter formatter);
}
