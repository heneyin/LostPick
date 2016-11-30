<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.username"
							var="userId"/>
<h2>修改个密码</h2>
<div class="add-notice-form">

<form action="<c:url value='/user/cp/${userId }'/>" method="post">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<table>
		<c:if test="${step == 1 }">
		<h3>第一步：确认旧密码</h3><br>
		<tr>
			<td>旧密码：</td>
			<td>
				<span class="error-message">${error }</span>
				<c:if test="${result==false }"><span>原密码错误！请重试</span></c:if>
				<input type="password" name="password" id="oldPassword"><br>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="hidden" name ="step" value="${step }">
				<input type="submit" value="确认">
			</td>
		</tr>
		</c:if>
		
		<c:if test="${step == 2 }">
		<h3>第二步：输入新密码</h3><br>
		<tr>
			<td>密码：</td>
			<td><input type="password" name=password id="password">
				<span id="passwordCheckResult"></span>
			</td>
		</tr>
		
		<tr>
			<td>确认密码：</td>
			<td>
				<input type="password" name=password0 id="password0">
				<span id="password0CheckResult"></span>
			</td>
		</tr>
		
		<tr>
			<td></td>
			<td>   	
				<input type="hidden" name ="step" value="${step }">
				<input type="submit" value="确认">
			</td>
		</tr>	
		</c:if>
	
	</table>	
</form>
</div>