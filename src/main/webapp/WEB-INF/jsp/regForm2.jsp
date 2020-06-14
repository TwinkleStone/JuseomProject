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
<h2>공연 참가 신청 - Step2</h2>

<form:form modelAttribute="regReq" method="post" action="step3">

<label for="type">공연 종류</label>:
<%-- <select name="type">
	<c:forEach var="t" items="${performerTypes}">
		<option value="${t}" 
			<c:if test="${t == regReq.type}">selected</c:if>>
			${t}
		</option>
	</c:forEach>
</select><br/> --%>  
<%-- 위와 같이 해도 되지만 다음과 같이 <form:select>를 이용하는 것이 간편함 --%>
<form:select path="type" items="${performerTypes}" />
<form:errors path="type"/> <br/> 
 
<label for="title">곡명</label>:
<form:input path="title" />
<form:errors path="title"/> <br/>

<label for="length">공연 시간</label>:
<form:input path="length" size="3"/>
<form:errors path="length"/> <br/>

<label>첫 공연?  
<%-- <form:checkbox path="newPerformer" value="true" checked="${newPerformer ? 'checked' : '' }"/>
 --%>
<form:checkbox path="newPerformer" />
</label><br/></br>

<a href="step1">[이전 단계로]</a> &nbsp;&nbsp;
<input type="submit" value="다음 단계로" />

</form:form>

</body>
</html>