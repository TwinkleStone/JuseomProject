<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<form:form modelAttribute="userForm"
		action="/juseom/user/mypage/profileUpload.do" method="post"
		enctype="multipart/form-data">
	이미지 파일: <input type="file" name="report" />
		<br />
		<input type="submit" value="Upload" />
	</form:form>
	<form:form modelAttribute="userForm" method="post" action="/juseom/user/mypage/edit.do">
		<form:errors cssClass="error" />
		<br>
		<br>
		<table id="user">
			<tr>
				<td>
					<h3>
						<font color="darkgreen">User Information</font>
					</h3>
					<table class="n13">
						<tr>
							<td>
							<c:if test="${not empty filename}">
							<img src="<c:url value='/upload/${filename}' />" />
							</c:if>
							<c:if test="${empty filename}">
							<img src="<c:url value='/upload/${userSession.user.profilePicUrl}' />" />
							</c:if>
							</td>
						</tr>
						<tr>
							<td>User ID:</td>
							<td>${userSession.user.userId}</td>
						</tr>
						<tr>
							<td>New password:</td>
							<td><form:password path="user.password" /> <B><form:errors
										path="user.password" cssClass="error" /></B></td>
						</tr>
						<tr>
							<td>Repeat password:</td>
							<td><form:password path="confirmPassword" /> <B><form:errors
										path="confirmPassword" cssClass="error" /></B></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><form:input path="user.name" /> <form:errors
									path="user.name" /></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><form:input path="user.phone" /> <form:errors
									path="user.phone" /></td>
						</tr>
						<tr>
							<td>주소</td>
							<td>
							<form:input path="user.address1" />
							<form:errors path="user.address1" />
							시/도&nbsp;
							<form:input path="user.address2" size="10" />
							<form:errors path="user.address2" />
							시/군/구&nbsp;
							<form:input path="user.address3" size="7" />
							<form:errors path="user.address3" />
							동/읍/면&nbsp;
							</td>
						</tr>
						<tr>
							<td>닉네임</td>
							<td><form:input path="user.commName" />
							<form:errors path="user.commName" /></td>
						</tr>
						<tr><td><input type="submit" value="수정하기" /></td></tr>
					</table>
				</td>
			</tr>
		</table>
		<br />
		<!-- 서브밋버튼 -->
	</form:form>
	<p></p>
	<h3>
		<b><a href='<c:url value="/user/chatList.do"/>'>채팅 리스트</a></b>
	</h3>
	<h3>
		<b><a href='<c:url value="/user/saleList.do"/>'>판매 리스트</a></b>
	</h3>

</div>