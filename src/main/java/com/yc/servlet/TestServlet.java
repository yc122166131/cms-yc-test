package com.yc.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
		
				String str = "&todopageaid=do0&urlhide=http%3A%2F%2Fportaltest2.sh.cmcc%2Fwps%2FPA_1_CM8P9FH20ORU20A71OF31M2000%2Fstatic%2Ffinish.html";
				
				String st = URLDecoder.decode(str, "UTF-8"); 
				
				//getParameterMap()��Ӧ��
				String[] favourates = request.getParameterMap().get("fav");
				for(int i = 0 ; i < favourates.length ; i++ ){
					System.out.println(favourates[i]);
				}
				
				System.out.println(request.getParameter("username") +"============"+request.getParameter("password"));
	
	}

}
