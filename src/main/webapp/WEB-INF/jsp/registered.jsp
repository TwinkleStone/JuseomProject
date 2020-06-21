<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입 접수</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style>
</head>
<body>
${userForm.user.userId}님의 회원 가입을 완료했습니다.<br/><br/>

접수 내용:
<ul>
	<li>프로필 사진: <img src="<c:url value='/upload/${userForm.user.profilePicUrl}' />" /></li> 
	<li>ID: ${userForm.user.userId}</li>
	<li>이름: ${userForm.user.name}</li> 
	<li>전화번호: ${userForm.user.phone}</li> 
	<li>주소: ${userForm.user.address1}시/도 ${userForm.user.address2}시/군/구 ${userForm.user.address3}동/읍/면
	<li>닉네임: ${userForm.user.commName}</li> 
</ul>
<p><a href="<c:url value='/loginForm' />">Go to schedule</a></p>
</body>
</html>