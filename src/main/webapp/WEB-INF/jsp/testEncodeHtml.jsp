<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


		<input  type="hidden" value="" id="todopageaid" />
		<input  type="hidden" value="" id="urlhide" />
		<input  type="button" value="点击 "  onclick="func1();" />
		
		<script>
		
			window.onload = function(){
				
				var $todopageaid = "${todopageaid}";
				var $urlhide = "${urlhide}";
				if($todopageaid!="" || $urlhide != "" ){
					document.getElementById("todopageaid").value = $todopageaid;
					document.getElementById("urlhide").value = $urlhide;
				}
				
			}
			
			
			function func1(){
				
				var dom1 = document.getElementById("todopageaid").value;
				
				var dom2 = document.getElementById("urlhide").value;
				
				var _myhref = dom2+ "?todotaskid="+ dom1;
				
				window.location.href = "${pageContext.request.contextPath}/test/testEncodeHtml2?todopageaid="+dom1+"&urlhide="+dom2;
			}
			
		
		</script>
		

</body>
</html>