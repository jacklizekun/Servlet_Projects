package utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static String getUUID64() {
		return getId() + getId();
	}

	public static String getCode() {
		return getId();
	}

}
