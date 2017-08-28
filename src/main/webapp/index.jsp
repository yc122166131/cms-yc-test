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
		
		<input type="submit" value="提交"/>
		<br>
</form>

</body>
</html>