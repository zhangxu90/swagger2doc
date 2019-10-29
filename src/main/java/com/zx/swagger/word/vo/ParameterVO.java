package com.zx.swagger.word.vo;

/**
 * Created by zx on 2019/10/28
 */
public class ParameterVO {
	// eg 参数名 dto
	private String name;

	// eg 数据类型 body
	private String dataType;

	// eg 参数类型 AuditSearchDto
	private String paraType;

	// eg 是否必填 Y
	private String required;

	// eg 说明 dto
	private String describe;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
