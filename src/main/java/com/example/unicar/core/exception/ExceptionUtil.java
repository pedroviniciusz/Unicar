package com.example.unicar.core.exception;

public class ExceptionUtil {

	public static <T> T requireField(T value, String message) {
        if (value == null)
            throw new IllegalArgumentException(message);
        return value;
    }

    public static <T> T exception(String message) {
        throw new JasperReportException(message);
    }

}
