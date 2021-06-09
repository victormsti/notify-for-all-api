package com.notifyforall.api.enums;

public enum NotificationOrigin {

	SERVICE("Service"),
	API("API");

	String value;

	NotificationOrigin(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
