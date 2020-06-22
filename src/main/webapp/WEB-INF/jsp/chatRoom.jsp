<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>채팅</title>
</head>
<body>
	<h2>채팅창</h2>
	
	
	<table>
	<c:forEach var="chat" items="${chatList}">
	<tr>
	<td>
	${chat.chat}
	</td>
	</tr>
	</c:forEach>
	</table>
	<form:form modelAttribute="chatting" action="/juseom/chat.do" method="post">
	<table>
	<tr>
	<td>
	<label for="chat">채팅 내용</label>:
	<form:input path="chat" />
	</td>
	<td>
	<input type="submit" value="Upload"/>
</td></tr>
	</table>
	</form:form>
	

</body>
</html>