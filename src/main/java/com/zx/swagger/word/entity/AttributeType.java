package com.zx.swagger.word.entity;

/**
 * Created by zx on 2019/10/24
 */
public class AttributeType {
	private String type;
	private String format;
	private String $ref;

	public String get$ref() {
		return $ref;
	}

	public void set$ref(String $ref) {
		this.$ref = $ref;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
