package com.example.unicar.core.exception;

public class ExceptionUtil {
	private ExceptionUtil() {}

	public static <T> T requireField(T value, String message) {
        if (value == null)
            throw new IllegalArgumentException(message);
        return value;
    }
}
