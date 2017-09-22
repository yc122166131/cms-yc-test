package com.yc.test.entity;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3296872705140317758L;

	
	private Long id;
	private String name;
	private String desc;
	private Double price;
	private Date createTime;
	
	public Test() {
		super();
	}
	public Test(String name, String desc, Double price, Date createTime) {
		super();
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}



