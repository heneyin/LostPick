<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<h2>
   		     个人中心
    </h2>
    <div class="pct-container">
        <div class="pct-sidebar">
            <div class="pct-portal">
                <a  href='<c:url value="/notice/pick/1"/>'>前往失物招领</a>
                <a  href='<c:url value="/notice/lost/1"/>'>前往寻物启事</a>
            </div>
            <div class="pct-user-info">
                <div class="pct-user-info-title">
                    <span>账号信息</span>
                    <sec:authorize access="hasRole('ROLE_USER')">
                    <a href="<c:url value='/user/updateInfo/${userInfo.userId }' />">编辑信息</a>
                    <a href="<c:url value='/user/cpf/${userInfo.userId }' />" >修改密码</a>
                    </sec:authorize>
                </div>
                <table>
                    <tr>
                        <td>账号：</td>
                        <td><span>${userInfo.userId } </span></td>
                    </tr>
                    <tr>
                        <td>昵称:</td>
                        <td><span>${userInfo.userName }</span></td>
                    </tr>
                    <tr>
                        <td>所在学院:</td>
                        <td><span>${userInfo.academy }</span></td>
                    </tr>
                    <tr>
                        <td>电话号</td>
                        <td><span>${userInfo.phonecode }</span></td>
                    </tr>
                    <%-- <tr>
                        <td>QQ: </td>
                        <td><span>${userInfo.QQNumber }</span></td>
                    </tr>
                    <tr>
                        <td>邮箱： </td>
                        <td><span>${userInfo.email }</span></td>
                    </tr> --%>
                </table>
            </div>
        </div>

        <div class="pct-notices-list">
            <div class="list-title">我发布的寻物启事</div>
            <ul>
            	<c:forEach items="${lostNotices }" var="list">
	                <li>
	                	<a href="<c:url value='/notice/detail/${list.id }' />">
	                		一个${list.goodsType }&nbsp; ${list.description } 
	                	</a>
	                	<span>${list.noticeTime }</span>
	                </li>
            	</c:forEach>
            </ul>
            <div class="list-title">我发布的失物招领</div>
            <ul>
                <c:forEach items="${pickNotices }" var="list">
	                <li>
	                	<a href="<c:url value='/notice/detail/${list.id }' />">
	                		一个${list.goodsType }&nbsp; ${list.description } 
	                	</a>
	                	<span>${list.noticeTime }</span>
	                </li>
            	</c:forEach>
            </ul>
        </div>
    </div>