package rml.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.service.MUserServiceI;

@Controller
@RequestMapping("/ftl")
public class FtlController {

	private MUserServiceI muserService;

	public MUserServiceI getMuserService() {
		return muserService;
	}

	@Autowired
	public void setMuserService(MUserServiceI muserService) {
		this.muserService = muserService;
	}
	
	@RequestMapping(value="/testFtl")
//	http://localhost:8080/zhibing_mybatis/ftl/testFtl.do
	public String testFtl(HttpServletRequest request , Model model) {
		model.addAttribute("message", "Model中添加，第一个模板实验");
		List<String> listStr = new ArrayList<String>();
		listStr.add("Happy");
		listStr.add("Andy");
		model.addAttribute("listStr", listStr);
		return "testFtl";
	}
}
