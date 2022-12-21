package com.example.unicar.core.exception;

import static com.example.unicar.core.util.IsNullUtil.isNull;

public class ExceptionUtil {

	public static <T> T requireField(T value, String message) {
        if (isNull(value))
            throw new IllegalArgumentException(message);
        return value;
    }

    public static <T> T exception(String message) {
        throw new JasperReportException(message);
    }

}
