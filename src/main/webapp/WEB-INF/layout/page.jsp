<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
		xmlns:th="http://www.thymeleaf.org">
<head>


<s:url value="/resources" var="resources"/>

<%--  common  公共部分--%>

	<title>${titlePrefix }<t:getAsString name="title"/> </title>
	
	<link rel="stylesheet" href="${resources }/css/style.css"/>
	<script src="${resources }/js/jquery-1.11.1.min.js"></script>

	<script src="${resources }/js/toast.js"></script>
		<!--form表单美化-->
	<link rel="stylesheet" href="${resources }/css/beautify-form.css"/>

<%--  /common  --%>
<%-- 为不同的页面添加不同的head内容 --%>


<c:if test="${pageName eq 'addNotice'}">
	<link rel="stylesheet" href="${resources }/css/button.css"/>

	<script src="${resources }/js/jquery-1.11.1.min.js"></script>
	<!--单选框美化-->
	<script src="${resources }/js/jquery-labelauty.js"></script>
	<!--时间选择器-->
	<script src="${resources }/js/date-select.js "></script>
	<!--上传图片前预览-->
	<script src="${resources }/js/image-preview.js"></script>
	
	<script src="${resources }/js/form-validator.js"></script>
	<script>
		$(function(){
			$('.add-notice-form input').labelauty();

			$('#preview').hide();

			$('#have-image').click(function(){
				$('#preview').show();
				$("#isHaveImage").val('true');

			})

			$('#no-image').click(function () {
				$('#preview').hide();
				$("#isHaveImage").val('false');
			})

			$('#submit').click(function () {
				//alert($('#description').val());
			})
		});
	</script>

	<style>
		#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
	</style>
</c:if>
<c:if test="${pageName eq 'register'}">
	<script src="${resources }/js/form-validator.js"></script>
</c:if>
<c:if test="${pageName eq 'login'}">
	<script src="${resources }/js/login-validator.js"></script>
</c:if>
<sec:csrfMetaTags/>

</head>
<body>
	<div class="header">
		<t:insertAttribute name="header"/>
	</div>
	
<c:if test="${pageName eq 'notices'}">
	<div class="nav">
    	<ul>
			<li><a href="<c:url value='/notice/${lop }/1'/>">所有</a></li>
			<li><a href="<c:url value='/notice/${lop }/钥匙/1'/>">钥匙</a></li>
			<li><a href="<c:url value='/notice/${lop }/手机/1'/>">手机</a></li>
			<li><a href="<c:url value='/notice/${lop }/校园卡/1'/>">校园卡</a></li>
			<li><a href="<c:url value='/notice/${lop }/身份证/1'/>">身份证</a></li>
			<li><a href="<c:url value='/notice/${lop }/银行卡/1'/>">银行卡</a></li>
			<li><a href="<c:url value='/notice/${lop }/手机/1'/>">手机</a></li>
			<li><a href="<c:url value='/notice/${lop }/钱包/1'/>">钱包</a></li>                          
			<li><a href="<c:url value='/notice/${lop }/包/1'/>">包</a></li>
			<li><a href="<c:url value='/notice/${lop }/其他/1'/>">其他</a></li>
   	 	</ul>
	</div>
</c:if>
	
	
	<div class="content">
		<t:insertAttribute name="body"/>
		<input id="toast-value" type="hidden" value="${toastValue }">
		<div class="toast"></div>
	</div>
	<div class="footer">
		<t:insertAttribute name="footer"/>
	</div>
</body>
</html>