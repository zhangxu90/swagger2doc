package com.zx.swagger.word.controller;


import com.zx.swagger.word.WordService;
import com.zx.swagger.word.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WordController {

	@Autowired
	private WordService wordService;

	@RequestMapping(path = "/preview", method = RequestMethod.POST)
	public String preview(@RequestParam String url, Model model) {
		List<Table> tables = wordService.fetchTablesFromUrl(url);
		model.addAttribute("tables", tables);
		return "word2";
	}

	@RequestMapping(path = "/word")
	public String word(HttpServletRequest request) {
		return "word2";
	}

}
