package com.yc.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;



/**
 * java解码 URLDecoder
 * @author yuanchen
 *
 */
public class ttt {

	public static void main(String[] args) {
			
		String str = "&todopageaid=do0&urlhide=http%3A%2F%2Fportaltest2.sh.cmcc%2Fwps%2FPA_1_CM8P9FH20ORU20A71OF31M2000%2Fstatic%2Ffinish.html";
		try {
			String st = URLDecoder.decode(str, "UTF-8");
			System.out.println(st);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
	}

}
