package com.yc.test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("restriction")
@XmlRootElement(name="yc_student")
public class StudentDto  implements Serializable{

	/**
	 * ��������� ���� �洢 List �� ���ݽṹ
	 */
	private static final long serialVersionUID = -2132260582736739232L;

	private List<Student> students =new ArrayList<Student>(0);

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
