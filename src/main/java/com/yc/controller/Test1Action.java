package com.yc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.test.entity.Test;
import com.yc.test.mapper.TestMapper;


@RequestMapping("/test")
@Controller
public class Test1Action {

	
	
	@Autowired
	private TestMapper testMapper;
	
	
	@RequestMapping(value = "/testGetJson")
	@ResponseBody
	public Map<String,List<String>> testGetJson(){
		
		Map<String,List<String>>  map1 = new HashMap<String,List<String>>();
		List<String> list = new ArrayList<String>();
		map1.put("aa", list);
		return map1;
	}
	
	
	@RequestMapping(value = "/testEncodeHtml")
	public String testSUFIXHtml(HttpServletRequest request){
		
		
		String _todopageaid = request.getParameter("todopageaid");
		String _urlhide =  request.getParameter("urlhide");
		
		if(!"".equals(_todopageaid)){
			request.setAttribute("todopageaid", _todopageaid);
		}
		if(!"".equals(_urlhide)){
			
		  /*	try {
				_urlhide = URLDecoder.decode(_urlhide, "UTF-8");
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
			request.setAttribute("urlhide", _urlhide);
		}
		
		return "testEncodeHtml";
		
	}
	
	@RequestMapping(value = "/testEncodeHtml2")
	public String testSUFIXHtml2(HttpServletRequest request){
		
		String _todopageaid = request.getParameter("todopageaid");
		String _urlhide =  request.getParameter("urlhide");
		
		if(!"".equals(_todopageaid)){
			request.setAttribute("todopageaid", _todopageaid);
		}
		if(!"".equals(_urlhide)){
			
		  	try {
				_urlhide = URLDecoder.decode(_urlhide, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			request.setAttribute("urlhide", _urlhide);
		}
		
		
		return "";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/saveTestDate1",method=RequestMethod.POST)
	public Map<String,Test> saveTestDate1(){
		Test t1 = new Test("sayGoodbye","z",new Double(6.66),new Date());
		
		Map<String,Test> obj = new HashMap<String,Test>();
		try{
			testMapper.addTestTbl(t1);
			obj.put("flage", t1);
		}catch(Exception e){
			e.printStackTrace();
			obj.put("flage", null);
		}
		return  obj;
	}
	
	
	
}
