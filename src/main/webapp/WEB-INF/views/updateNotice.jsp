<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<s:url value="/user" var="userSources" />
	<h2>
		编辑启事
	</h2>

	<div class="add-notice-form">
		<form  action="<c:url value='/notice/update'/>?${_csrf.parameterName}=${_csrf.token}" method="POST" id="addNoticeForm" name="addNoticeForm" enctype="multipart/form-data">
			<%-- <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> --%>
			<table>
				<tr>
					<td><p>你想要的发布的是：</p></td>
					<td>
						<ul>
							<li><form:radiobutton path="notice.lostOrPick" value="true" data-labelauty="寻物启事"/></li>
							<li><form:radiobutton path="notice.lostOrPick" value="false" data-labelauty="失物招领"/></li>
						</ul>
						<form:errors path="notice.lostOrPick" cssClass="error-message"/>
						
					</td>
				</tr>
				<tr>
					<td><p>物品类型：</p></td>
					<td>
						<form:select path="notice.goodsType" >
							<form:option value="ps" label="请选择"></form:option>
							 <form:options  items="${goodsTypeOptions }"/>
						</form:select>
						<form:errors path="notice.goodsType" cssClass="error-message"/>
						<span id="goodsTypeCheckResult"></span> 
					</td> 
				</tr>

				<tr>
					<td><p>丢或者捡的时间：</p></td>
					<td>
						<form:select path="notice.YYYY" onChange="YYYYDD(this.value)">
							<form:option value="-" label="请选择-年"/>
						</form:select>
						<form:select path="notice.MM" onChange="MMDD(this.value)" >
							<form:option value="-" label="请选择-月"/>
						</form:select>
						<form:select path="notice.DD">
							<form:option value="-" label="请选择-日"/>
						</form:select>
						<%-- <form:errors path="notice.lopTime" cssClass="error-message"/> --%>
						<span id="lopTimeCheckResult"></span>
					</td>
				</tr>

				<tr>
					<td><p>具体时间描述：</p></td>
					<td>
						<form:input path="notice.exactTime" cssErrorClass="input-error"/>
						<form:errors path="notice.exactTime" cssClass="error-message"/>
						<span id="exactTimeCheckResult"></span>
					</td>
				</tr>

				<tr>
					<td><p>具体地点：</p></td>
					<td>
						<form:input path="notice.lopPlace" cssErrorClass="input-error"/>
						<form:errors path="notice.lopPlace" cssClass="error-message"/>
						<span id="lopPlaceCheckResult"></span> 
					</td>
				</tr>

				<tr>
					<td><p>图片：</p></td>
					<td>

						 <div id="preview">
							<img id="imghead" name="image-preview" width="210" height="220" src='<c:url value="${userSources }/${notice.imgUrl }"/>'>
						</div> 

						 <a href="javascript:;" class="choose-image-button">修改物品照片
						 <input type="file" name="imageFile" id="have-image" onchange="getPhotoSize(this)" >
					 
						 </a> 

						<a href="javascript:;" id="no-image" class="choose-image-button">
							我不修改照片
						</a>
						<input type="hidden" id="isHaveImage" name="isHaveImage" value="false"> 
					</td>
				</tr>

				<tr>
					<td><p>物品详细描述：</p></td>
					<td>
						<form:textarea path="notice.description" cols="59" rows="8"/>
						<form:errors path="notice.description" cssClass="error-message"/>
						<span id="descriptionCheckResult"></span> 
					</td>
				</tr>

				<tr>
					<td>
                  	    <form:hidden path="notice.userId"/>
                  	    <form:hidden path="notice.id"/>
						
						<input type="submit" value="提交" id="submit">
						<span class="submit-message"></span>
					</td>
				</tr>
			</table>


		</form>
	</div>