<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>修改个人信息</h2>
<div class="register-form">
		<form action="<c:url value='/user/updateInfo/${user.userId} '/>" id="updateUserInfo" name="updateUserInfo" method="post">
			<input type="hidden" name="sex" value="1">
			<table>
				<tr>
					<td>账号：</td>
					<td>
						<p>${user.userId }</p>
					</td>
				</tr>
				<tr>
					<td>昵称：</td>
					<td>
						<form:input path="user.userName"/>
						<form:errors path="user.userName" cssClass="error-message"/>
						<span id="userNameCheckResult"></span>
					</td>
				</tr>

				<tr>
					<td>学院：</td>
					<td>
						<form:select  path="user.academy"  cssErrorClass="input-error">
							<form:option value="ps" label="请选择"></form:option>
							 <form:options  items="${academys }" />
						</form:select>
   						<form:errors path="user.academy" cssClass="error-message"/>
   						<span id="academyCheckResult"></span>
					</td>
				</tr>

				<tr>
					<td>手机号码：</td>
					<td>
						<form:input path="user.phonecode" items="${phonecode }"/>
   						<form:errors path="user.phonecode" cssClass="error-message"/>
   						<span id="phonecodeCheckResult"></span><br>
					</td>
				</tr>
<%-- 
				<tr>
					<td>QQ号：</td>
					<td>
						<form:input path="user.QQNumber"/>
  						<form:errors path="user.QQNumber"/>
					</td>
				</tr>

				<tr>
					<td>邮箱：</td>
					<td>
						<form:input type="email" path="user.email"/>
  						<form:errors path="user.email"/>
					</td>
				</tr> --%>
				
				
				<tr>
					<td></td>
					<td >
						<input type="hidden" name="sex" value="1">
						<input type="hidden" name="password" value="0000000"><!-- 这里只是临时数据 -->
						<input type="hidden" name="userId" value="${user.userId }">
						<input type="hidden" name="QQNumber" value="11111111">
						<input type="hidden" name="email" value="111111@qq.com">
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
   						<input type="submit" value="提交注册" id="submitBtn" >
   						<span class="submit-message"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>