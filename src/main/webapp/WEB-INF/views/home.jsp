<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:url value="/resources" var="resources"/>
<s:url value="/" var="adminResource"/>
        <!--两个链接-->
       <div class="two-link">
           <ul>
               <li><a href='<c:url value="/notice/pick/1"/>'>查看失物招领</a></li>
               <li><a href='<c:url value="/notice/lost/1"/>' >查看寻物启事</a></li>
           </ul>
       </div>
	
       <div class="day-word">
           <img src="${adminResource }${dayWord.imgUrl}" alt="" height="284" width="318"/>
           <div class="day-word-sentence">
               <p>${dayWord.sentence}</p>
               <span>by ${dayWord.author}</span>
           </div>
       </div>
