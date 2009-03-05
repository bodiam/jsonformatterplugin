package net.javaisp.intellij.plugin.jsonformatter.format.compact.handlers;

import com.sdicons.json.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Maintains the list of all {@linkplain CompactJsonFormatterHandler}s.
 *
 * @author Cristian Vasile Mocanu
 */
public final class Handlers {
    private static final Map<Class<? extends JSONValue>, CompactJsonFormatterHandler> handlers = initializeHandlers();

    private Handlers() {
        // prevent instantiations: this class should only have static methods
    }

    private static Map<Class<? extends JSONValue>, CompactJsonFormatterHandler> initializeHandlers() {
        Map<Class<? extends JSONValue>, CompactJsonFormatterHandler> result = new HashMap<Class<? extends JSONValue>, CompactJsonFormatterHandler>();

        registerHandler(result, JSONNull.class, new NullHandler());
        registerHandler(result, JSONBoolean.class, new BooleanHandler());
        registerHandler(result, JSONInteger.class, new IntegerHandler());
        registerHandler(result, JSONDecimal.class, new DecimalHandler());
        registerHandler(result, JSONString.class, new StringHandler());
        registerHandler(result, JSONArray.class, new ArrayHandler());
        registerHandler(result, JSONObject.class, new ObjectHandler());

        return result;
    }

    private static <T extends JSONValue> void registerHandler(Map<Class<? extends JSONValue>, CompactJsonFormatterHandler> handlers, Class<T> handlerClass, CompactJsonFormatterHandler<T> handler) {
        handlers.put(handlerClass, handler);
    }

    public static CompactJsonFormatterHandler getHandler(Class<? extends JSONValue> jsonValueClass) {
        return handlers.get(jsonValueClass);
    }
}
