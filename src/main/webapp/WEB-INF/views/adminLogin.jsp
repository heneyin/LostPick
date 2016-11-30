<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员注册</title>
</head>
<body>
<form action="<c:url value='/admin/login'/>" method="post">
name：
	<input type="text" name="name">
password:
	<input type="password" name="password">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<input type="submit" value="登录">
</form>

</body>
</html>