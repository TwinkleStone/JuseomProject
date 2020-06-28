<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>입찰 form</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/animate.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/aos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icomoon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">×</button>
    	<h3 class="smaller lighter blue no-margin modal-title">입찰정보 입력</h3>
	</div>

	<div class="modal-body">
		<div class="container">
		 <div class="row justify-content-center">
		   <div class="co-md-4" style="padding: 30px">
			<form:form modelAttribute="bidder" action="bidding.do" method="post" class="bg-light p-5 contact-form">
				<div class="form-group" style="width: 500px">
					<label for="bidNumber">입찰할 책의 갯수 *</label>
					<form:input path="bidNumber" class="form-control"/>
					<form:errors path="bidNumber"/> <br/>
				</div>
				<div class="form-group" style="width: 500px">
					<label for="bidPrice">입찰가 *</label>
					<form:input path="bidPrice" class="form-control" />
					<form:errors path="bidPrice"/> <br/>
				</div>
				<form:hidden path="auctionId" value="${param.auctionId}" />
				<div class="form-group" style="text-align:center">
					<input type="submit" value="입찰하기" class="btn btn-primary py-3 px-4">
				</div>
			</form:form>
		  </div>
		 </div>
		</div>
	</div>
	
	<div class="modal-footer">
    	<button class="btn btn-sm btn-danger pull-right" data-dismiss="modal" id="btnClose">
       		<i class="ace-icon fa fa-times"></i>닫기
    	</button>
	</div>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

  	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/scrollax.min.js"></script>
  	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


</body>
</html></html>