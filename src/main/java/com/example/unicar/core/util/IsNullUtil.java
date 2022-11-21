package com.example.unicar.core.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class IsNullUtil {
	public static boolean isNull(Object value) {
		return value == null;
	}

	public static boolean isNotNull(Object value) {
		return value != null;
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().length() == 0);
	}

	public static boolean isNullOrEmpty(Object value) {
		return (value == null);
	}

	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return collection.stream().anyMatch(Objects::nonNull);
	}

	public static boolean isNullOrEmpty(Number number) {
		return isNull(number) || (number.doubleValue() <= 0);
	}

	public static <T> boolean isNullOrEmpty(Map<T, T> map) {
		return isNull(map) || (map.isEmpty());
	}

	public static boolean isNullOrEmpty(Object[] array) {
		return isNull(array) || (array.length == 0);
	}

}
