package com.yc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("test")
@Controller
public class Test1Action {

	
	//如果报 406错误  是因为 没有@responseBody 必须要加 jackson jar包
	
	@RequestMapping(value = "testGetJson")
	@ResponseBody
	public Map<String,List<String>> testGetJson(){
		
		Map<String,List<String>>  map1 = new HashMap<String,List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("你好歹1");
		list.add("你好歹2");
		list.add("你好歹3");
		list.add("你好歹4");
		list.add("你好歹5");
		map1.put("aa", list);
		
		return map1;
	}
	
	
}
