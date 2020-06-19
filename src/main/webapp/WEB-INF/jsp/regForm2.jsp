<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 가입</title>
</head>
<body>
	<h2>회원 가입 - Step2</h2>
	
	<form:form modelAttribute="userForm" action="/juseom/user/register/profileUpload.do" method="post" enctype="multipart/form-data">
	이미지 파일: <input type="file" name="report" />
	<br/>
	<input type="submit" value="Upload"/>
	</form:form>
	<c:if test="${not empty filename and not empty userForm.report}">
	업로드된 이미지 파일: <br>
	<img src="<c:url value='/upload/${filename}' />" /> 
	</c:if>
	<c:if test="${not empty userForm.report and empty filename}">
	<img src="<c:url value='/upload/${userForm.user.profilePicUrl}' />" />
	</c:if>
	<form:form modelAttribute="userForm" method="post" action="/juseom/user/register/confirm.do" enctype="multipart/form-data">

		<label for="commName">닉네임</label>:
		<form:input path="user.commName" />
		<form:errors path="user.commName" />
		<br /></br>

		<a href="step1.do">[이전 단계로]</a> &nbsp;&nbsp;
		<input type="submit" value="다음 단계로" />

	</form:form>

</body>
</html>