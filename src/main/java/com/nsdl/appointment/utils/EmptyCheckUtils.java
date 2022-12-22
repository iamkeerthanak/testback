package com.nsdl.appointment.utils;

import java.util.Collection;
import java.util.Map;

public final class EmptyCheckUtils {

	private EmptyCheckUtils() {
		super();
	}

	public static boolean isNullEmpty(Object obj) {
		return obj == null;
	}

	public static boolean isNullEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNullEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNullEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

}
