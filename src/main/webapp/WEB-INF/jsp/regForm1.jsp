<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
</head>
<body>
<h2>회원 가입 - Step1</h2>

<form:form modelAttribute="userForm" method="post" action="/juseom/user/register/step2.do">
	<label for="userId">ID(이메일 주소)</label>: 
	<form:input path="user.userId" />
	<form:errors path="user.userId"/> <br/>
	
	<label for="password">암호</label>: 
	<form:password path="user.password" />
	<form:errors path="user.password"/> <br/>
	
	<label for="confirmPassword">암호 확인</label>: 
	<form:password path="confirmPassword" />
	<form:errors path="confirmPassword"/>
	<br/>  
	
	<label for="name">이름</label>: 
	<form:input path="user.name" />
	<form:errors path="user.name"/> <br/>
	
	<label for="phone">전화번호</label>:
	<form:input path="user.phone" />
	<form:errors path="user.phone"/> <br/>
	
	<label>주소</label>:
	<form:input path="user.address1" />
	<form:errors path="user.address1"/>
	시/도&nbsp;
	<form:input path="user.address2" size="10"/>
	<form:errors path="user.address2"/> 
	시/군/구&nbsp;
	<form:input path="user.address3" size="7" />
	<form:errors path="user.address3"/>
	동/읍/면&nbsp;<br/>
	<br/>
	<input type="submit" value="다음 단계로" />

</form:form>

</body>
</html>