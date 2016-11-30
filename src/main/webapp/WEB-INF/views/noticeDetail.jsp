<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:url value="/user" var="userSources" />
    <h2>
      		${titlePrefix }
    </h2>

	<div class="detail">		
		<a href="Javascript:history.go(-1);void(0);"> 返回上一页</a><br/>
		<img src="${userSources }/${notice.imgUrl }" alt="" />
		
		<div class="detail-txt-container">
			<div class="detail-txt">
				<div class="detail-title">
                    <h3>一个${notice.goodsType }</h3>
                    <p>启事发布时间：<span>${notice.noticeTime }</span></p>
                    <p>发布人：<span>${user.userName }</span></p>
                    <p>联系电话：<span>${user.phonecode }</span></p>
                </div>
                <p>${word}时间：<span>${notice.lopTime } ${notice.exactTime }</span></p>
				<p>${word}地点：<span>${notice.lopPlace }</span></p>
 				<%--<p>QQ：${user.QQNumber }</p> --%>
				<p>描述：<span>${notice.description }</span></p>
			</div>
		</div>
		<div class="detail-button-div">
			<sec:authorize  access="hasRole('ROLE_USER')">
				<sec:authentication property="principal.username"
							var="loginUserId"/>
			</sec:authorize>
				<c:if test="${loginUserId eq user.userId }">
				<a href="<c:url value='/notice/delete/${notice.id }_${user.userId }'/>" onclick="return confirm('确认删除该启事？');">
					<button class="detail-button detail-delete-button">删除</button>
				</a>
				<a href="<c:url value='/notice/bupdate/${notice.id }_${user.userId }'/>">
					<button class="detail-button detail-modify-button">修改</button>
				</a>
			</c:if>
		</div>
	</div>	