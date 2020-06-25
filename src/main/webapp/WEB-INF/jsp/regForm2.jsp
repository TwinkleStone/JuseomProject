<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원 가입</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/animate.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/aos.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/flaticon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/icomoon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
	<div class="container">
		<a class="navbar-brand" href="<c:url value="/index.do" />">주섬주섬</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#ftco-nav" aria-controls="ftco-nav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="oi oi-menu"></span> Menu
		</button>

		<div class="collapse navbar-collapse" id="ftco-nav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="<c:url value="/index.do" />"
					class="nav-link">중고</a></li>
				<li class="nav-item"><a href="<c:url value="/auction.do" />"
					class="nav-link">경매</a></li>
				<li class="nav-item"><a href="<c:url value="/share.do" />"
					class="nav-link">나눔</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/insert/search.do" />">등록</a></li>
				<c:if test="${!empty userSession.user}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/user/mypage.do" />">마이페이지</a></li>
				</c:if>
				<c:if test="${empty userSession.user}">
					<li class="nav-item active"><a class="nav-link"
						href="<c:url value="/user/loginForm.do" />">로그인/회원가입</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<div class="hero-wrap"
		style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_test8.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row no-gutters slider-text justify-content-center align-items-center">
				<div class="col-lg-8 col-md-6 ftco-animate d-flex align-items-end">
					<div class="text text-center w-100">
						<h1 class="mb-4">
							Find Properties <br>That Make You Money
						</h1>
					</div>
				</div>
			</div>
		</div>
		<div class="mouse">
			<a href="#" class="mouse-icon">
				<div class="mouse-wheel">
					<span class="ion-ios-arrow-round-down"></span>
				</div>
			</a>
		</div>
	</div>

	<!-- 회원가입 입력폼 -->
	<section class="ftco-section goto-here">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12 heading-section text-center ftco-animate mb-5">
				<h2 class="mb-2">Register - STEP2</h2>
				<br />

				<table class="table table-hover" style="text-align: center">
					<tr>
						<td><c:if
								test="${not empty filename and not empty userForm.report}">
	업로드된 이미지 파일: <br>
								<img src="<c:url value='/upload/${filename}' />" />
							</c:if> <c:if test="${not empty userForm.report and empty filename}">
								<img
									src="<c:url value='/upload/${userForm.user.profilePicUrl}' />" />
							</c:if></td>
					</tr>
				</table>
				<form:form modelAttribute="userForm"
					action="/juseom/user/register/profileUpload.do" method="post"
					enctype="multipart/form-data">
					<table class="table table-hover" style="text-align: center">
						<tr>
							<td>이미지 파일: <input type="file" name="report" /> <input type="submit" value="Upload" />
							</td>
						</tr>
					</table>
				</form:form>
				<form:form modelAttribute="userForm" method="post"
					action="/juseom/user/register/confirm.do"
					enctype="multipart/form-data">
					<table class="table table-hover" style="text-align: center">
						<tr>
							<td rowspan="2"><label for="commName">닉네임</label>:</td>
							<td><form:input path="user.commName" /></td>
						</tr>
						<tr>
							<td><form:errors path="user.commName" /></td>
						</tr>
						<tr>
							<td colspan="2"><a href="step1.do">[이전 단계로]</a> &nbsp;&nbsp;
								<input type="submit" value="다음 단계로" /></td>
						</tr>
					</table>
				</form:form>
				<footer class="ftco-footer ftco-section">
				<div class="container">
					<div class="row mb-5">
						<div class="col-md">
							<div class="ftco-footer-widget mb-4">
								<h2 class="ftco-heading-2">Findstate</h2>
								<p>Far far away, behind the word mountains, far from the
									countries.</p>
								<ul class="ftco-footer-social list-unstyled mt-5">
									<li class="ftco-animate"><a href="#"><span
											class="icon-twitter"></span></a></li>
									<li class="ftco-animate"><a href="#"><span
											class="icon-facebook"></span></a></li>
									<li class="ftco-animate"><a href="#"><span
											class="icon-instagram"></span></a></li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="ftco-footer-widget mb-4 ml-md-4">
								<h2 class="ftco-heading-2">Community</h2>
								<ul class="list-unstyled">
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Search Properties</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>For Agents</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Reviews</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>FAQs</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="ftco-footer-widget mb-4 ml-md-4">
								<h2 class="ftco-heading-2">About Us</h2>
								<ul class="list-unstyled">
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Our Story</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Meet the team</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Careers</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="ftco-footer-widget mb-4">
								<h2 class="ftco-heading-2">Company</h2>
								<ul class="list-unstyled">
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>About Us</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Press</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Contact</a></li>
									<li><a href="#"><span
											class="icon-long-arrow-right mr-2"></span>Careers</a></li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="ftco-footer-widget mb-4">
								<h2 class="ftco-heading-2">Have a Questions?</h2>
								<div class="block-23 mb-3">
									<ul>
										<li><span class="icon icon-map-marker"></span><span
											class="text">203 Fake St. Mountain View, San
												Francisco, California, USA</span></li>
										<li><a href="#"><span class="icon icon-phone"></span><span
												class="text">+2 392 3929 210</span></a></li>
										<li><a href="#"><span class="icon icon-envelope pr-4"></span><span
												class="text">info@yourdomain.com</span></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">

							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="icon-heart color-danger" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>
				</div>
				</footer>



				<!-- loader -->
				<div id="ftco-loader" class="show fullscreen">
					<svg class="circular" width="48px" height="48px"> <circle
						class="path-bg" cx="24" cy="24" r="22" fill="none"
						stroke-width="4" stroke="#eeeeee" /> <circle class="path" cx="24"
						cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
						stroke="#F96D00" /></svg>
				</div>

				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
				<script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/scrollax.min.js"></script>
				<script
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/js/main.js"></script>

				<!--
  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
    -->
</body>
</html>