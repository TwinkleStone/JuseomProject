<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html><head>
<title>로그인</title>
</head>
<body>
<div align="center">
  <form action='<c:url value="/user/login.do"/>' method="POST">
<%--     <c:if test="${!empty signonForwardAction}">
      <input type="hidden" name="forwardAction"
        value='<c:url value="${signonForwardAction}"/>' />
    </c:if> --%>
    <table>
      <tr>
        <td colspan="2">Please enter your username and password. <br />&nbsp;
        </td>
      </tr>
      <tr>
        <td>ID:</td>
        <td><input type="text" name="userId" size="20"/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" size="20"/></td>
      </tr>
    </table>
    <input type="submit" value="로그인" /> <a href="<c:url value="/user/register/step1.do" />">회원 가입</a><br>
  </form>
  
  
</div>


<%-- 	<form:form modelAttribute="loginUser" action="/user/login.do">
		<label for="userId">ID(이메일 주소)</label>: 
		<form:input path="userId"/>
		<form:errors path="userId"/>
		<br/>
		<label for="password">암호</label>: 
		<form:password path="password" />
		<form:errors path="password" />
		<br/>
		<input type="submit" value="로그인" />
		<a href="<c:url value="/user/register.do" />">회원 가입</a><br>
	</form:form> --%>
</body></html>