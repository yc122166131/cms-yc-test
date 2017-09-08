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

	
	//����� 406����  ����Ϊ û��@responseBody ����Ҫ�� jackson jar��
	
	@RequestMapping(value = "testGetJson")
	@ResponseBody
	public Map<String,List<String>> testGetJson(){
		
		Map<String,List<String>>  map1 = new HashMap<String,List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("��ô�1");
		list.add("��ô�2");
		list.add("��ô�3");
		list.add("��ô�4");
		list.add("��ô�5");
		map1.put("aa", list);
		
		return map1;
	}
	
	
}
