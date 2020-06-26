<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별점 평가 완료 후 확인하는 창</title>
</head>
<body>
${rate.raterId}님이 ${rate.ratedId}님에게 별점 남기기를 성공하였습니다.<br/>

별점 내용: 
<ul>
	<li>별점 : ${rate.rate}</li>
	<li>한줄평가: ${rate.description}</li>
</ul>
</body>
</html>