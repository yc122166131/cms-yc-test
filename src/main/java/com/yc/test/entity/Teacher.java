package com.yc.test.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Teacher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203820581512236149L;
	
	private String  name ;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
