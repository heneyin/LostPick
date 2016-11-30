<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<s:url value="/user" var="user" />

	<h2>
     	  ${titlePrefix}
    </h2>
    
 	 <div class="notices">

    <c:forEach items="${noticeList}" var="list">   <!--class="col-md-3 team-grid"  -->
        
        <c:if test="${list.imgUrl != 'null' }">
        <a href='<c:url value="/notice/detail/${list.id }"/>' title="查看启事">
            <div class="notice" >
                <img src="${user }/${list.imgUrl }" alt=""/>
				 <p>${word}物品：${list.goodsType}</p>
				 <p>${word}时间：${list.lopTime} ${list.exactTime }</p>
				 <p>${word}地点：${list.lopPlace}</p>
            </div>
        </a>
        </c:if>

        <c:if test="${list.imgUrl eq 'null' }">
        <a  href='<c:url value="/notice/detail/${list.id }"/>'>
            <div class="notice">
                <div class="no-image">
                    <p>
                       	描述：${list.description }
                    <p>
                </div>
				 <p>${word}物品：${list.goodsType}</p>
				 <p>${word}时间：${list.lopTime} ${list.exactTime }</p>
				 <p>${word}地点：${list.lopPlace}</p>
            </div>
   		 </a>
        </c:if>
     </c:forEach>
     

       <%-- <div class="sub-page-count">
       		<span><c:if test="${pageNumber == 1 }">老大</c:if></span>
  			<c:if test="${pageNumber > 1 }"><a href='<c:url value="/notice/${lop }/1"/>'>首页</a></c:if>
    
  			<c:if test="${pageNumber == 1 }">上一页</c:if>
  			<c:if test="${pageNumber > 1 }"><a href='<c:url value="/notice/${lop }/${pageNumber - 1 }"/>'>上一页</a></c:if>
    
  			<c:if test="${pageNumber == maxPage }">下一页</c:if>
   			<c:if test="${pageNumber < maxPage }"><a href='<c:url value="/notice/${lop }/${pageNumber + 1 }"/>'>下一页</a></c:if>
  			<span><c:if test="${pageNumber == maxPage }">末页</c:if></span>
   			<c:if test="${pageNumber < maxPage }"><a href='<c:url value="/notice/${lop }/${maxPage }"/>'>末页</a></c:if>
       </div> --%>
    </div>
    <div class="sub-page-count">
    	<c:if test="${pageNumber > 1 }"><a class="left" href='<c:url value="/notice/${lop }/${pageNumber - 1 }"/>'>上一页</a></c:if>
   		${pageNumber}
   		<c:if test="${pageNumber < maxPage }"><a class="right" href='<c:url value="/notice/${lop }/${pageNumber + 1 }"/>'>下一页</a></c:if>
    </div>
    
	

	<div class="goto-box">
		<c:if test="${word eq '捡到' }">
			<a href='<c:url value="/notice/lost/1"/>'>前往寻物启事
			</a>
		</c:if>
		
		<c:if test="${word eq '丢失' }">
			<a href='<c:url value="/notice/pick/1"/>'>前往失物招领
			</a>
		</c:if>
	</div>	
