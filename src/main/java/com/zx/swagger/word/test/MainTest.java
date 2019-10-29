//package com.zx.swagger.word.test;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.zx.swagger.word.entity.ApiJson;
//import com.zx.swagger.word.entity.AttributeType;
//import com.zx.swagger.word.entity.Attributes;
//import com.zx.swagger.word.vo.ParameterVO;
//import com.zx.swagger.word.vo.ResponseVO;
//import com.zx.swagger.word.vo.TableVO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.util.Assert;
//import org.springframework.web.client.RestTemplate;
//
//import java.lang.reflect.InvocationTargetException;
//import java.nio.charset.Charset;
//import java.util.*;
//
//public class MainTest {
//	public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//
//		// 准备设置utf8为返回结果字符集的 RestTemplate
//		RestTemplate restTemplate = MainTest.getInstance();
//		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8000/v2/api-docs", String.class);
//
//		// 获得体并初始化实体
//		String body = forEntity.getBody();
//		ApiJson entity = JSON.parseObject(body, ApiJson.class);
//
//		// 取出定义，后续会用到
//		Map<String, Attributes> definitions = entity.getDefinitions();
//
//		// 定义前段所用实体
//		List<TableVO> tableVOS = new ArrayList<>();
//
//		// eg k:v / test1:vo
//		// 在swagger里 一个接口的 不同请求方式，算不同接口，这里合并成一个
//		Map<String, TableVO> tableVOMap = new HashMap<>();
//
//		// 遍历请求URL
//		entity.getPaths().forEach((path, methods) -> {
//			// 遍历
//			methods.forEach((methodName, method) -> {
//
//				// 当发现 同一地址有不同请求方式时，只记录请求方式
//				TableVO tableVO = tableVOMap.get(path);
//				if (tableVO == null) {
//					tableVO = new TableVO();
//					tableVOMap.put(path, tableVO);
//				} else {
//					tableVO.setMethod("," + tableVO.getMethod());
//					return;
//				}
//
//				tableVO.setTag(StringUtils.join(method.getTags(), ","));
//				tableVO.setDescribe(method.getDescription());
//				tableVO.setUrl(path);
//				tableVO.setMethod(methodName);
//				tableVO.setRequestType(StringUtils.join(method.getConsumes(), ","));
//				tableVO.setResponseType(StringUtils.join(method.getProduces(), ","));
//
//				// 请求参数示例
//				JSONObject requestPara = new JSONObject();
//
//				List<ParameterVO> parameterVOS = new ArrayList<>();
//				Arrays.asList(method.getParameters()).forEach(parameter -> {
//					String description = parameter.getDescription(); // user
//					String in = parameter.getIn();// body
//					String pName = parameter.getName();// user
//					boolean required = parameter.isRequired();// true
//					String type = parameter.getType(); // null
//
//					AttributeType schema = parameter.getSchema(); // type=null	format=null $ref=#/definitions/User
//					if (schema == null) {
//						requestPara.put(pName, type);
//					} else {
//						String $ref = schema.get$ref();
//						if (StringUtils.isNotBlank($ref)) {
//							requestPara.fluentPutAll(getRef(definitions, $ref));
//						}
//					}
//
//					ParameterVO parameterVO = new ParameterVO();
//					parameterVO.setName(pName);
//					parameterVO.setDataType(type);
//					parameterVO.setParaType(in);
//					parameterVO.setRequired(required ? "Y" : "N");
//					parameterVO.setDescribe(description);
//
//					parameterVOS.add(parameterVO);
//				});
//				tableVO.setParameters(parameterVOS);
//
//				// 返回参数示例
//				JSONObject responseResult = new JSONObject();
//
//				List<ResponseVO> responseVOS = new ArrayList<>();
//				method.getResponses().forEach((responseName, response) -> {
//					ResponseVO responseVO = new ResponseVO();
//					responseVO.setStatusCode(responseName);
//					responseVO.setDescribe(response.getDescription());
//					AttributeType schema = response.getSchema();
//					if (schema == null) {
//
//					} else {
//						String $ref = schema.get$ref();
//						if (StringUtils.isNotBlank($ref)) {
//							responseResult.fluentPutAll(getRef(definitions, $ref));
//						}
//						responseVO.setRemark(schema.get$ref());
//					}
//
//					responseVOS.add(responseVO);
//				});
//				tableVO.setResponses(responseVOS);
//				tableVO.setRequestExample(requestPara.toJSONString());
//				tableVO.setResponseExample(responseResult.toJSONString());
//				tableVOS.add(tableVO);
//			});
//		});
//		System.out.println();
//	}
//
//	private static JSONObject getRef(Map<String, Attributes> definitions, String $ref) {
//		Assert.notNull($ref, "the $ref is null");
//		String beanName = StringUtils.substringAfterLast($ref, "/");
//		Attributes attributes = definitions.get(beanName);
//		Assert.notNull(attributes, $ref + " ref is not exist");
//		Map<String, AttributeType> properties = attributes.getProperties();
//		Assert.notNull(properties, $ref + " ref can not find properties");
//
//		JSONObject tmp = new JSONObject();
//		properties.forEach((k, v) -> {
//			String $ref1 = v.get$ref();
//			String type = v.getType();
//			if ($ref1 == null) {
//				tmp.put(k, type);
//			} else {
//				tmp.put(k, getRef(definitions, $ref1));
//			}
//
//		});
//		return tmp;
//	}
//
//	public static RestTemplate getInstance() {
//		RestTemplate restTemplate = new RestTemplate();
//		List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
//		for (HttpMessageConverter<?> httpMessageConverter : list) {
//			if (httpMessageConverter instanceof StringHttpMessageConverter) {
//				((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
//				break;
//			}
//		}
//		return restTemplate;
//	}
//}
