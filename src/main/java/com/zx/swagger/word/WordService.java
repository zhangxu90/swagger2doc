package com.zx.swagger.word;

import com.alibaba.fastjson.JSON;
import com.zx.swagger.word.entity.ApiJson;
import com.zx.swagger.word.vo.Table;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2019/10/28
 */
@Service
public class WordService {

	private RestTemplate restTemplate;

	public List<Table> fetchTablesFromUrl(String url) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
		ApiJson entity = JSON.parseObject(forEntity.getBody(), ApiJson.class);
		List<Table> tables = new ArrayList<>();
		entity.getPaths().forEach((path, methods) -> {
			methods.forEach((name, method) -> {
				Table table = new Table();
				String tag = StringUtils.join(method.getTags(), ",");
				table.setTag(tag);
//				String describe;
//				String url;
//				String method;
//				String requestType;
//				String responseType;
//				List<Parameter> parameters;
//				List<Response> responses;
//				String requestExample;
//				String responseExample;
				tables.add(table);
				System.out.println();
			});
			System.out.println();
		});
		System.out.printf("");
		return tables;
	}
}
