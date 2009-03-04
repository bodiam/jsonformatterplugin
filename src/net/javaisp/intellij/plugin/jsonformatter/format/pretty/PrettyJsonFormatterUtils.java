package net.javaisp.intellij.plugin.jsonformatter.format.pretty;

import com.sdicons.json.model.*;

/**
 * Contains various utility methods.
 *
 * @author Cristian Vasile Mocanu
 */
public final class PrettyJsonFormatterUtils {
    private PrettyJsonFormatterUtils() {
        // private constructor to prevent instantiation of this utility class
    }

    /**
     * @param value the {@code JSONValue} to check
     *
     * @return true if and only if {@code value} is a "primitive" type (i.e. it does not have sub-elements).
     *         The primitive types are:
     *             {@link JSONNull},
     *             {@link JSONBoolean},
     *             {@link JSONInteger},
     *             {@link JSONDecimal} and
     *             {@link JSONString}.
     */
    public static boolean isPrimitiveJsonType(JSONValue value) {
        return !(value instanceof JSONComplex);
    }

    /**
     * @param object the object who's class name we want
     *
     * @return the name of the class of {@code object}, or {@code null} if {@code object} is null
     */
    public static String safeClassName(Object object) {
        if (object == null) {
            return "null";
        } else {
            return object.getClass().getName();
        }
    }

    /**
     * Appends a given number of space characters (\s) to the given {@code StringBuilder} 
     *
     * @param result       the place to put the characters to
     * @param indentSpaces the number of space characters to append to {@code result}
     */
    public static void indent(StringBuilder result, int indentSpaces) {
        for (int i = 0; i < indentSpaces; i++) {
            result.append(" ");
        }
    }

    /**
     * Formats the given {@code String} as a JSON string. The special characters (\n, \t etc.) are escaped and the
     * result string is surrounded by double quotes.
     *
     * @param result the place to put the JSON string to
     * @param string the {@code String} to format
     */
    public static void formatString(StringBuilder result, String string) {
        if (string == null) {
            result.append("\"\"");
            return;
        }

        result.append("\"");

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '\n') {
                result.append("\\n");
            } else if (c == '\r') {
                result.append("\\r");
            } else if (c == '\t') {
                result.append("\\t");
            } else if (c == '\b') {
                result.append("\\b");
            } else if (c == '\f') {
                result.append("\\f");
            } else if (c == '\"') {
                result.append("\\\"");
            } else if (c == '\\') {
                result.append("\\\\");
            } else {
                result.append(c);
            }
        }

        result.append("\"");
    }
}
