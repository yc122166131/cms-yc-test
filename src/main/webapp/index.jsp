<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<form action="${pageContext.request.contextPath }/testServlet" method="post">

		<input   type="text" id="username" name="username" value=""   />
		<br>
		<input type="password" id="password" name="password" value="">
		<br>
		体育 :<input  type="checkbox" id="fav1" name="fav"  value="ty" />
		数学 :<input  type="checkbox" id="fav2" name="fav"  value="sx" />
		语文 :<input  type="checkbox" id="fav3" name="fav"  value="yw" />
		英语 :<input  type="checkbox" id="fav4" name="fav"  value="eng" />
		音乐 :<input  type="checkbox" id="fav5" name="fav"  value="music" />
		<br>
		
		<input type="submit" value="提交" id="aa1"/>
		<br>
		<br>
		
		<!--  ajax 和  getJson 返回json 数据 -->
		<a href="javascript:void(0);" onclick=javascript:testGetJSON();> 测试$.getJson方法 </a>
		<input type="button" value="点击"  onclick="btnCl();" />
		
		
		<!--  下面是 跨域-->
		<p><input type="button" value="exec main function" onclick="exec_main()"></p>  
		
</form>

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	
		    var params = 'nihao1111111';
		    
			// 在主页面跨域调用子iframe页面时调用的方法
			function fIframe(){   
			  alert('iframe function execute success');  
			}  
			
			// exec main function  
			function exec_main(){  
			  if(typeof(exec_obj)=='undefined'){  
			      exec_obj = document.createElement('iframe');  
			      exec_obj.name = 'tmp_frame';  
			      exec_obj.src = 'http://127.0.0.1:8020/aaa/execA.html?p='+params;  
			      exec_obj.style.display = 'none';  
			      document.body.appendChild(exec_obj); 
			      
			  }/* else{  
			      exec_obj.src = 'http://127.0.0.1:8020/aaa/execA.html?' + Math.random();  
			  }   */
			}  
					
		
		
		
		
		
		
		
		
		
		
		function btnCl(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/test/testGetJson.action",
				data:{},
				dataType:"json",
				success:function(data){
					console.log(data);
					/* da1.forEach(function(item,index,array){
						console.log(item);
					}); */
					console.log("+=====");
					console.log(Object.prototype.toString.call(data));
				},
				erroe:function(msg){
					alert(msg);
				}
			})
		}
		
		
		function testGetJSON(){
			$.getJSON("${pageContext.request.contextPath}/test/testGetJson.action","",function(msg){
				console.log(msg);
				console.log(Object.prototype.toString.call(msg));
			});
		}
</script>

</body>
</html>