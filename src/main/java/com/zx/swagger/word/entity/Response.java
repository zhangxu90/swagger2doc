package com.zx.swagger.word.entity;

/**
 * Created by zx on 2019/10/24
 */
public class Response {
	private String description;
	private AttributeType schema;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AttributeType getSchema() {
		return schema;
	}

	public void setSchema(AttributeType schema) {
		this.schema = schema;
	}
}
