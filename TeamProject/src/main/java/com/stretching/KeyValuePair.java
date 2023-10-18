package com.stretching;

import lombok.Data;

@Data
public class KeyValuePair{
	private String key;
	private String value;
	
	public KeyValuePair(String key, String value) {
		this.key = key;
		this.value = value;
	}
}