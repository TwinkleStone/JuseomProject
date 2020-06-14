<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>공연 참가 신청</title>
</head>
<body>
<h2>공연 참가 신청 - Step1</h2>

<form:form modelAttribute="regReq" method="post" action="step2">
<label for="id">ID(이메일 주소)</label>: 
<form:input path="id" />
<form:errors path="id"/> <br/>

<label for="name">이름</label>: 
<form:input path="name" />
<form:errors path="name"/> <br/>

<label for="password">암호</label>: 
<form:password path="password" />
<form:errors path="password"/> <br/>

<label for="confirmPassword">암호 확인</label>: 
<form:password path="confirmPassword" />
<form:errors path="confirmPassword"/>
<form:errors path="samePasswordConfirmPassword"/>   <!-- @AssertTrue 에 의한 오류 발생 시 출력  --> 
<br/>  

<label for="phone">전화번호</label>:
<form:input path="phone" />
<form:errors path="phone"/> <br/>

<label>주소</label>:
street
<form:input path="address.street" />
<form:errors path="address.street"/>&nbsp;
city
<form:input path="address.city" size="10"/>
<form:errors path="address.city"/>&nbsp; 
zipcode
<form:input path="address.zipcode" size="7" />
<form:errors path="address.zipcode"/> <br/>
<br/>
<input type="submit" value="다음 단계로" />

</form:form>

</body>
</html>