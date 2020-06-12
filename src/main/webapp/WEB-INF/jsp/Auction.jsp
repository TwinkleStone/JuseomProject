<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>경매 설정</title>
</head>
<body>
<table class="top">
  <tr>
    <td style="text-align:left">
      <form action="<c:url value="/shop/searchProducts.do"/>" method="post">
	    <input type="hidden" name="search" value="true"/>
        <input type="text" name="endTime" size="14" />&nbsp;
      </form>
    </td>
  </tr>
</table>

</body>
</html>