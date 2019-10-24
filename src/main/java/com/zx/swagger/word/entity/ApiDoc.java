package com.zx.swagger.word.entity;

import java.util.Map;

public class ApiDoc {
	private String basePath;
	private String host;
	private String swagger;
	private Map<String, Attributes> definitions;
	private Info info;
	private Map<String, Map<String, Method>> paths;
	private Tag[] tags;

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public Map<String, Map<String, Method>> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, Map<String, Method>> paths) {
		this.paths = paths;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Map<String, Attributes> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Map<String, Attributes> definitions) {
		this.definitions = definitions;
	}

	public String getSwagger() {
		return swagger;
	}

	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
