package com.zx.swagger.word.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zx on 2019/10/24
 */
public class Attributes {
	private Map<String, AttributeType> properties = new HashMap<>();
	private String title;
	private String type;

	public Map<String, AttributeType> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, AttributeType> properties) {
		this.properties = properties;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

