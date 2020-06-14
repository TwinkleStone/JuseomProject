<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>공연 참가 신청</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style>
</head>
<body>
<h2>공연 참가 신청 - Step3</h2>

<form:form modelAttribute="regReq" method="post" action="done">

다음 정보로 신청하시겠습니까?<br/><br/>

ID: ${regReq.id} <br/>
이름: ${regReq.name} <br/>
전화번호: ${regReq.phone} <br/>
주소: ${regReq.address.street} ${regReq.address.city} 
		 (우편번호: ${regReq.address.zipcode})<br/>
공연 종류: ${regReq.type} <br/>
곡명: ${regReq.title} <br/>
공연 시간: ${regReq.length} <br/>
첫 공연 여부: ${regReq.newPerformer}<br/>	

<br>
<a href="step2">[이전 단계로]</a> &nbsp;&nbsp;
<input type="submit" value="확인" />

</form:form>

</body>
</html>