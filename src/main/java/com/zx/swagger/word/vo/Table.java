package com.zx.swagger.word.vo;

import java.util.List;

/**
 * Created by zx on 2019/10/28
 */
public class Table {

	// eg 手机端日志查询
	private String tag;

	// eg 接口描述	手机端日志查询
	private String describe;

	// eg URL	/mobile/idtoken/audit/search
	private String url;

	// eg 请求方式	post
	private String method;

	// eg 请求类型	application/json
	private String requestType;

	// eg 返回类型	*/*
	private String responseType;

	private List<Parameter> parameters;

	private List<Response> responses;

	// eg {...}
	private String requestExample;

	// eg {...}
	private String responseExample;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public String getRequestExample() {
		return requestExample;
	}

	public void setRequestExample(String requestExample) {
		this.requestExample = requestExample;
	}

	public String getResponseExample() {
		return responseExample;
	}

	public void setResponseExample(String responseExample) {
		this.responseExample = responseExample;
	}
}
