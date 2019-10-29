package com.zx.swagger.word.controller;


import com.zx.swagger.word.service.WordService;
import com.zx.swagger.word.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class WordController {

	@Autowired
	private WordService wordService;


	@RequestMapping(path = "/preview", method = RequestMethod.POST)
	public String preview(@RequestParam String url, @RequestParam(required = false) boolean needButton, Model model) {
		List<TableVO> tableVOS = wordService.fetchTablesFromUrl(url);
		model.addAttribute("tables", tableVOS);
		model.addAttribute("needButton", needButton);
		model.addAttribute("url", url);
		return "preview";
	}

	@RequestMapping(path = "/download", method = RequestMethod.POST)
	public void download(@RequestParam String url, HttpServletResponse response) throws IOException {
		String html = wordService.fetchHtmlFromUrl(url);
		ServletOutputStream outputStream = response.getOutputStream();
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("swagger.doc", "utf-8"));
			outputStream.write(html.getBytes());
		} finally {
			outputStream.flush();
			outputStream.close();
		}
	}

}
