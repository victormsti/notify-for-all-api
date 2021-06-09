package com.notifyforall.api.enums;

public enum ChannelType {

	WEB_PUSH("Web Push"),
	EMAIL("E-mail"),
	SMS("SMS");
	
	String value;

	ChannelType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
