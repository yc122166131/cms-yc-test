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
		
		<!--  cookie -->
		<p> <input type="button" id="cookieBtn"  value="cookie测试" /></p>
		<br>
		<input  type="button" value="获取cookie"   onclick="javascript:getCookie_tmp();"  />
		<br/>
		<input  type="button" value="销毁cookie"   onclick="javascript:destroyCookie();"  />
		
		<input   type="button"  value="jsonp请求"   id="jsonpBtn"   onclick="javascript:jsonpFunc();" />
		
		<br/>
		<input type="button" value="测试encodeUrl" onclick="testEncodeUrl();">
</form>

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/jquery.cookie.min.js"></script> -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
			
			
			$(function(){
				
				$("#cookieBtn").click(function(){
					//$.cookie('ooo', 'cookieValue111');  
					//$.cookie('the_cookie', 'the_value', { expires: 7 });
					$.cookie('cookie2017', '2017', { expires: 7, path: '/',secure:true });
				});
				
			});
			
			
			
			
			function testEncodeUrl(){
				 window.open("${pageContext.request.contextPath}/test/testEncodeHtml.action?tranId=87678567a&todopageaid=do0&urlhide=http%3A%2F%2Fportaltest2.sh.cmcc%2Fwps%2FPA_1_CM8P9FH20ORU20A71OF31M2000%2Fstatic%2Ffinish.html");
				
			}
			
			
			
			function jsonpFunc(){
					
				$.ajax({
					type : "GET",
					url : "http://localhost:8866/testRequestMapping/getT/1989aa",
					dataType : "jsonp",
					jsonp:"ycCallback",
					success : function(data){
						console.log(data);
						//alert(Object.prototype.toString.call(data));
						for(var key in data){
							alert("key: " + key + "value: "+data[key]);
						}
					},
					error:function(msg){
						console.log("error!");
					}
				});
			}
			
			
			function getCookie_tmp(){
				var tmp = $.cookie("cookie2017");
				console.log(tmp);
			}
			
			
			
			function destroyCookie(){
				 name = "the_cookie";
				 $.cookie(name,null);
			}
			
			//========================== js跨域相关  start =======================================
			
		    var params = 'nihao666';
		    
			// 在主页面跨域调用子iframe页面时调用的方法
			function fIframe(){   
			  alert('iframe function execute success');  
			}  
			
			// exec main function  
			// (execA.jsp  作为跨域 的 中间媒介 )
			function exec_main(){  
			  if(typeof(exec_obj)=='undefined'){  
			      exec_obj = document.createElement('iframe');  
			      exec_obj.name = 'tmp_frame';  
			      exec_obj.src = 'http://localhost:8866/execA.jsp?p='+params;  
			      exec_obj.style.display = 'none';  
			      document.body.appendChild(exec_obj); 
			      
			  }
			  /* else{  
			      exec_obj.src = 'http://localhost:8866/execA.jsp?' + Math.random();  
			 	 }   */
			  
			  
			  //window.location.href = "https://www.baidu.com";
				
			}  
					
			
			//==========================  end   js跨域相关   =======================================
			
			
			
				
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