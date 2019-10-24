package com.zx.swagger.word.entity;

import java.util.Map;

/**
 * Created by zx on 2019/10/24
 */
public class Method {
	private String[] consumes;
	private boolean deprecated;
	private String description;
	private String operationId;
	private String summary;
	private String[] tags;
	private Parameter[] parameters;
	private String[] produces;
	private Map<String,Response> responses;

	public String[] getConsumes() {
		return consumes;
	}

	public void setConsumes(String[] consumes) {
		this.consumes = consumes;
	}

	public boolean isDeprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Parameter[] getParameters() {
		return parameters;
	}

	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}

	public String[] getProduces() {
		return produces;
	}

	public void setProduces(String[] produces) {
		this.produces = produces;
	}

	public Map<String, Response> getResponses() {
		return responses;
	}

	public void setResponses(Map<String, Response> responses) {
		this.responses = responses;
	}
}
