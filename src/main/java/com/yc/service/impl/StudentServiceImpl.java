package com.yc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.yc.test.entity.Hobby;
import com.yc.test.entity.Student;
import com.yc.test.entity.StudentDto;
import com.yc.test.entity.Teacher;



/*元注解的几种解释：

@POST –服务接受处理POST 请求
@Path – web服务的URL路径，抓取URL Url <base_url>/bookservice/getbook/{name} , 增加:<base_url>/bookservice/addbook
@Produces – 指示响应的MIME类型，在案例中是 application/xml 和 application/json.
@Consumes – 这个服务能消费的请求的MIME类型*/


/**
 * cxf restful 形式的server端 service
 * @author yuanchen
 *
 */
@Service("studentService")
@Path("/studentService") //����Ǹ���Ŀ�� �� ��һ��·��
public class StudentServiceImpl  {
	
	
	/*
	 * client  请求代码
	 * 
	 * StudentDto stu = client.path("studentService/getStuInfo/333")
	.accept(contentType).get(StudentDto.class);
	
	*/
	
	/**
	 * 传递的值 放在 url中(常用于GET提交)
	 * 形式： /aaa/bbb/1
	 * @param name
	 * @return
	 */
    @GET
	@Path("/getStuInfo/{name}")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
	public Response getStudentInfo(@PathParam("name") String name) {
		
		System.out.println("==========:"+name);
		
		StudentDto outDto = null;
		if(!"0".equals(name)){
			outDto = new StudentDto();
			List<Student> list = new ArrayList<Student>();
			//第一组
			Student stu1 = new Student();
			Teacher t1 = new  Teacher();
			t1.setName("王老师");
			t1.setPassword("12345");
			Hobby h1 = new Hobby();
			h1.setFav("体育");
			h1.setId(1);
			Hobby h2 = new Hobby();
			h2.setFav("语文");
			h2.setId(2);
			Hobby h3 = new Hobby();
			h3.setFav("数学");
			h3.setId(3);
			
			stu1.setTeacher(t1);
			List<Hobby> lhobby = new ArrayList<Hobby>();
			lhobby.add(h1);
			lhobby.add(h2);
			lhobby.add(h3);
			stu1.setHobbies(lhobby);
			stu1.setName("小啊");
			stu1.setPassword("520");
			list.add(stu1);
			
			
			
			//第二组
			Student stu2 = new Student();
			Teacher t2 = new  Teacher();
			t2.setName("张老师");
			t2.setPassword("1234566");
			Hobby h4 = new Hobby();
			h4.setFav("旅游");
			h4.setId(4);
			Hobby h5 = new Hobby();
			h5.setFav("日语");
			h5.setId(5);
			Hobby h6 = new Hobby();
			h6.setFav("英语");
			h6.setId(6);
			
			stu2.setTeacher(t2);
			List<Hobby> llhobby = new ArrayList<Hobby>();
			llhobby.add(h4);
			llhobby.add(h5);
			llhobby.add(h6);
			stu2.setHobbies(llhobby);
			stu2.setName("zhouhang");
			stu2.setPassword("yc520");
			list.add(stu2);
			
			outDto.setStudents(list);
		}
	    if(outDto == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(outDto).build();
        }
		
	}
	
	
	
    
   //==========================下面是POST形式 提交 ()========================================
    
    
    /*
     *   client  请求代码
     *   Student stu2 = new Student();
		 stu2.setName("张三1");
		 StudentDto stu = client.path("studentService/getStuInfoByPost")
			.accept(contentType).post(stu2,StudentDto.class);
	*/
    
	/**
	 * 传递的值 作为 入参传入 (常用于POST提交)
	 * @param name
	 * @return
	 */
	@POST
	@Path("/getStuInfoByPost")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
	public Response getStudentInfoByPost(Student stu) {
		
		System.out.println(stu);
		
		Student s = null;
		StudentDto sDto = new StudentDto();
		if(stu!=null) {
			if("张三".equals(stu.getName())){
				s = new Student();
				s.setName("张三return");
			}
			List<Student> lstu = new ArrayList<Student>();
			lstu.add(s);
			sDto.setStudents(lstu);
		}
		
		return Response.ok(sDto).build();
		
	}
		
		
		
	
}
