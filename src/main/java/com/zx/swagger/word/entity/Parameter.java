package com.zx.swagger.word.entity;

/**
 * Created by zx on 2019/10/24
 */
public class Parameter {
	private String description;
	private String in;
	private String name;
	private boolean required;
	private String type;
	private AttributeType schema;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AttributeType getSchema() {
		return schema;
	}

	public void setSchema(AttributeType schema) {
		this.schema = schema;
	}
}
