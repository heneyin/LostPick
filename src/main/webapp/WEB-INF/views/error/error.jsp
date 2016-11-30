<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<center>
<h1>出错了，原因：${errorMessage }<a href=" <c:url value='/home' /> ">点击返回主页</a></h1>
</center>