package com.zx.swagger.word.vo;

/**
 * Created by zx on 2019/10/28
 */
public class Response {
	// eg 状态码 200
	private String statusCode;

	// eg 描述 OK
	private String describe;

	// eg 说明 OK
	private String remark;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
