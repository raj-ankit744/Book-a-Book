<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	${error}
	<form method="post" action="/login">
		<input type="email" name="email" placeholder="your email" value="${requestScope.email}"/>
		<input type="password" name="password" placeholder="your password" value="${requestScope.password}"/>
		<input type="submit" value="submit"/>
	</form> 
</body>
</html>