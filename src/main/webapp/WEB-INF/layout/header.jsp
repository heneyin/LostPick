<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--header-->
        <div class="header-content">

            <div class="logo">
                <a href=" <c:url value='/home' /> " title="进入主页">
                    <h1>LostPick</h1>
                </a>
            </div>

            <ul>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value='/user/login' />" >登录</a></li> 
					<li><a href="<c:url value='/user/register' />" >注册</a></li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_USER')">
					<sec:authentication property="principal.userRealName"
						var="realName"/>
					<sec:authentication property="principal.username"
						var="userId"/>
					<li>
						<a href="<c:url value='/user/personalCenter/${userId } ' />" >
							${realName }的个人中心
						</a>
					</li> 
							
					<li>
						<form action="<c:url value='/logout' />" method="post">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
							<input type ="submit" value="注销" />
						</form>
					</li>
				</sec:authorize>
				<li><a href="<c:url value='/notice/addNotice'/> ">发布</a></li>
            </ul>
        </div>