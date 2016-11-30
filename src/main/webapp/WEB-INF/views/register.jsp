<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<h2>
		注册
	</h2>
	<div class="register-form">
		<form action='<c:url value="/user/register" />' method="post" id ="registerForm">
			<input type="hidden" name="sex" value="密">
			<table>
				<tr>
					<td>账号：</td>
					<td>
						<form:input path="user.userId" cssErrorClass="input-error" />
						<form:errors path="user.userId" cssClass="error-message"/>
						<span id="userIdCheckResult"></span>
					</td>
					
				</tr>

				<tr>
					<td>密码：</td>
					<td>
						<form:password path="user.password" cssErrorClass="input-error" />
						<form:errors path="user.password" cssClass="error-message"/>
						<span id="passwordCheckResult"></span>
					</td>
				</tr>

				<tr>
					<td>确认密码：</td>
					<td>
						<input type="password" name="password0" id="password0">
						<span id="password0CheckResult"></span>
					</td>
				</tr>

				<tr>
					<td>名字：</td>
					<td>
						<form:input path="user.userName" cssErrorClass="input-error"/>
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
						<form:input path="user.phonecode" cssErrorClass="input-error"/>
   						<form:errors path="user.phonecode" cssClass="error-message"/>
						<span id="phonecodeCheckResult"></span><br>
					</td>
				</tr>
<%-- 				<tr>
					<td>QQ号：</td>
					<td>
						<form:input path="user.QQNumber" cssErrorClass="input-error"/>
  						<form:errors path="user.QQNumber" cssClass="error-message"/>
					</td>
				</tr> --%>

<%-- 				<tr>
					<td>邮箱：</td>
					<td>
						<form:input type="email" path="user.email" cssErrorClass="input-error" />
  						<form:errors path="user.email" cssClass="error-message"/>
					</td>
				</tr> --%>
				
				<tr>
					<td></td>
					<td><img id="codePicture" src="<c:url value='/codePicture' />" alt="" width="100" height="35"/></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td>
					<input type="text" name="codePicture" id="codePicture">
					<a href="javascript:;" onClick="reloadCode()">换一张</a>
					<c:if test="${pictureCodeError == true }">	
						<span class="error-message">${codeMessage }</span>
					</c:if>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td >
						<input type="hidden" name="QQNumber" value="11111111"><!-- 先不用这两个信息 -->
						<input type="hidden" name="email" value="111111@qq.com">
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
   						<input type="submit" value="提交注册" id="submitBtn" >
   						<span class="submit-message"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>