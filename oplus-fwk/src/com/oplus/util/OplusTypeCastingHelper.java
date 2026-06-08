package com.oplus.util;

public final class OplusTypeCastingHelper {
    private OplusTypeCastingHelper() {
    }

    public static <T> T typeCasting(Class<T> type, Object obj) {
        if (type != null && type.isInstance(obj)) {
            return type.cast(obj);
        }
        return null;
    }
}
