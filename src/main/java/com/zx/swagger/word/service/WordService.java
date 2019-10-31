package com.zx.swagger.word.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zx.swagger.word.entity.ApiJson;
import com.zx.swagger.word.entity.AttributeType;
import com.zx.swagger.word.entity.Attributes;
import com.zx.swagger.word.vo.ParameterVO;
import com.zx.swagger.word.vo.ResponseVO;
import com.zx.swagger.word.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zx on 2019/10/28
 */
@Service
public class WordService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${server.port}")
	private Integer port;

	public String fetchHtmlFromUrl(String url) {
		String s = restTemplate.postForObject("http://localhost:" + port + "/preview?url=" + url, null, String.class);
		return s;
	}

	public Map<String, List<TableVO>> groupingByTag(List<TableVO> tableVOS) {
		Map<String, List<TableVO>> collect = tableVOS.stream().collect(Collectors.groupingBy(TableVO::getTag));
		return collect;
	}

	public List<TableVO> fetchTablesFromUrl(String url) {

		// 准备设置utf8为返回结果字符集的 RestTemplate
		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

		// 获得体并初始化实体
		String body = forEntity.getBody();
		ApiJson entity = JSON.parseObject(body, ApiJson.class);

		// 取出定义，后续会用到
		Map<String, Attributes> definitions = entity.getDefinitions();

		// 定义前段所用实体
		List<TableVO> tableVOS = new ArrayList<>();

		// eg k:v / test1:vo
		// 在swagger里 一个接口的 不同请求方式，算不同接口，这里合并成一个
		Map<String, TableVO> tableVOMap = new HashMap<>();

		// 遍历请求URL
		entity.getPaths().forEach((path, methods) -> {
			// 遍历请求方式
			methods.forEach((methodName, method) -> {

				// 当发现 同一地址有不同请求方式时，只记录请求方式
				TableVO tableVO = tableVOMap.get(path);
				if (tableVO == null) {
					tableVO = new TableVO();
					tableVOMap.put(path, tableVO);
				} else {
					tableVO.setMethod(tableVO.getMethod() + "," + methodName);
					return;
				}

				// 设置基本信息
				tableVO.setTag(StringUtils.join(method.getTags(), ","));
				tableVO.setDescribe(method.getDescription());
				tableVO.setUrl(path);
				tableVO.setMethod(methodName);
				tableVO.setRequestType(StringUtils.join(method.getConsumes(), ","));
				tableVO.setResponseType(StringUtils.join(method.getProduces(), ","));

				// 请求参数示例
				JSONObject requestPara = new JSONObject();

				// 请求参数相关填充
				List<ParameterVO> parameterVOS = new ArrayList<>();
				Arrays.asList(method.getParameters()).forEach(parameter -> {
					String description = parameter.getDescription(); // user
					String in = parameter.getIn();// body
					String pName = parameter.getName();// user
					boolean required = parameter.isRequired();// true
					String type = parameter.getType(); // null

					AttributeType schema = parameter.getSchema(); // type=null	format=null $ref=#/definitions/User
					if (schema == null) {
						requestPara.put(pName, type);
					} else {
						String $ref = schema.get$ref();
						if (StringUtils.isNotBlank($ref)) {
							requestPara.fluentPutAll(getRef(definitions, $ref));
						}
					}

					ParameterVO parameterVO = new ParameterVO();
					parameterVO.setName(pName);
					parameterVO.setDataType(type);
					parameterVO.setParaType(in);
					parameterVO.setRequired(required ? "Y" : "N");
					parameterVO.setDescribe(description);

					parameterVOS.add(parameterVO);
				});
				tableVO.setParameters(parameterVOS);

				// 返回参数示例
				JSONObject responseResult = new JSONObject();
				StringBuffer hasResponse = new StringBuffer();

				// 返回相关填充
				List<ResponseVO> responseVOS = new ArrayList<>();
				method.getResponses().forEach((responseName, response) -> {
					ResponseVO responseVO = new ResponseVO();
					responseVO.setStatusCode(responseName);
					responseVO.setDescribe(response.getDescription());
					AttributeType schema = response.getSchema();
					if (schema == null) {
						// nothing to do
					} else {
						hasResponse.append(schema.getType());
						String $ref = schema.get$ref();
						if (StringUtils.isNotBlank($ref)) {
							responseResult.fluentPutAll(getRef(definitions, $ref));
						}
						responseVO.setRemark(schema.get$ref());
					}

					responseVOS.add(responseVO);
				});
				tableVO.setResponses(responseVOS);

				tableVO.setRequestExample(requestPara.size() == 0 ? "" : JSON.toJSONString(requestPara, true));
				tableVO.setResponseExample(hasResponse.length() > 0 ? responseResult.size() == 0 ? hasResponse.toString() : JSON.toJSONString(responseResult, true) : "");
				tableVOS.add(tableVO);
			});
		});

		return tableVOS;
	}

	private JSONObject getRef(Map<String, Attributes> definitions, String $ref) {
		Assert.notNull($ref, "the $ref is null");
		String beanName = StringUtils.substringAfterLast($ref, "/");
		Attributes attributes = definitions.get(beanName);
		Assert.notNull(attributes, $ref + " ref is not exist");
		Map<String, AttributeType> properties = attributes.getProperties();
		Assert.notNull(properties, $ref + " ref can not find properties");

		JSONObject tmp = new JSONObject();
		properties.forEach((k, v) -> {
			String $ref1 = v.get$ref();
			String type = v.getType();
			if ($ref1 == null) {
				tmp.put(k, type);
			} else if ($ref.equals($ref1)) {
				tmp.put(k, "{}");
			} else {
				tmp.put(k, getRef(definitions, $ref1));
			}

		});
		return tmp;
	}
}
