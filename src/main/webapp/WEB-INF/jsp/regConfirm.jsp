<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style>
</head>
<body>
<h2>회원 가입 - Step3</h2>

<form:form modelAttribute="userForm" method="post" action="/juseom/user/register/registered.do">

다음 정보로 신청하시겠습니까?<br/><br/>
프로필 사진: 
<c:if test="${not empty userForm.report and not empty filename}">
	<img src="<c:url value='/upload/${userForm.user.profilePicUrl}' />" /> <br/>
</c:if>
<c:if test="${empty userForm.report and empty filename}">
	<img src="<c:url value='/upload/Person.jpg' />" /> <br/>
</c:if>
ID: ${userForm.user.userId} <br/>
이름: ${userForm.user.name} <br/>
전화번호: ${userForm.user.phone} <br/>
주소: ${userForm.user.address1} 시/도 ${userForm.user.address2} 시/군/구
		 ${userForm.user.address3} 동/읍/면<br/>
닉네임: ${userForm.user.commName} <br/>

<br>
<a href="step2.do">[이전 단계로]</a> &nbsp;&nbsp;
<input type="submit" value="확인" />

</form:form>

</body>
</html>