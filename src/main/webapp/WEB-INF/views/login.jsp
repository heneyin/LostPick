<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:url value="/resources" var="resources"/>
<!-- 登录-->
	<h2>
		登录 
	</h2>
	<div class="login-box">
		<img src="${resources }/images/temp0.jpg" alt=""/>
		<div class="login-form">
			<form action="#" method="POST" onsubmit="return loginSubmit(this)">
				<table>
				<c:if test="${fn:length(sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message) ne '0'}" >

					<span class="login-fail">请输入正确的账号与密码！</span>
				</c:if> 
					
					<tr>
						<td><p>用户名:</p></td>
						<td>
						<input type="text" name="userId"><br>
						<span id="userIdCheckResult"></span>
						</td>
						
					</tr>

					<tr>
						<td><p>密码:</p></td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
							<input type="submit" value="登录" >
							<a href="<c:url value='/user/register'/>">注册</a>
							<!-- <a href="#">忘记密码？</a> -->
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>