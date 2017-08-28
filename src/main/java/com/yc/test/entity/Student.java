package com.yc.test.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6949618645934003827L;
	private String name;
	private String password;
	private Teacher teacher;
	private List<Hobby> hobbies;
	
	
	
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
}
