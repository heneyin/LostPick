<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>每日一句</title>
</head>
<body>
<c:if test="${key eq 'yes' }">
<form action="<c:url value='/dayWord/add'/>?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
图片
	<input type="file" name="imageFile">
句子：
	<input type="text" name="sentence">
作者：
	<input type="text" name="author">
	<%-- <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> --%>
	<input type="submit" value="添加">
</form>
</c:if>
错了错了！
<input>
</body>
</html>