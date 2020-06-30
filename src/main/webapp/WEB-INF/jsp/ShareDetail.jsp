<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>주섬주섬</title>
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
<style>
.star_rating {
	font-size: 0;
	letter-spacing: -4px;
}

.star_rating span {
	font-size: 22px;
	letter-spacing: 0;
	display: inline-block;
	margin-left: 5px;
	color: #ccc;
	text-decoration: none;
}

.star_rating span:first-child {
	margin-left: 0;
}

.star_rating span.on {
	color: #777;
}
</style>
<script>
	function openReview(){
		var review = document.getElementById("review");
		if(review.style.display=='none'){
			review.style.display = 'block';
		}else{
			review.style.display = 'none';
		}
	}
	function postJson() {
		var applier = {applierId:-1, userId:null, shareId:${share.shareId}}
		var jsonStr = JSON.stringify(applier);	
		var reqUrl = "../apply.do";
		
		$.ajax({
			type: "post",
			url: reqUrl,
			contentType: "application/json",
			data: jsonStr,
			processData: false,
			success: function(responseJson){	// object parsed from JSON text	
				alert("신청했습니다.");
				$("#peopleNumber").text("(신청수 : " + responseJson.peopleNumber + ")");
			},
			error: function(){
				alert("신청은 한번만 할 수 있습니다.");
			}
		});
	}
  </script>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
	<div class="container">
		<a class="navbar-brand" href="<c:url value="/index.do" />">주섬주섬</a>
		<c:if test="${!empty userSession.user}">
			<a class="navbar-brand1" href="<c:url value="/user/logout.do" />">로그아웃</a>
		</c:if>
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
				<li class="nav-item active"><a
					href="<c:url value="/share.do" />" class="nav-link">나눔</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/insert/search.do" />">등록</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/search.do" />">검색</a></li>
				<c:if test="${!empty userSession.user}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/user/mypage.do" />">마이페이지</a></li>
				</c:if>
				<c:if test="${empty userSession.user}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/user/loginForm.do" />">로그인/회원가입</a></li>
				</c:if>
				<!--  <li class="nav-item"><a href="properties.html" class="nav-link">Listing</a></li>
	          <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li> -->
			</ul>
		</div>
	</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap"
		style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_5.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row no-gutters slider-text justify-content-center align-items-center">

			</div>
		</div>
	</div>

	<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="col-md-12 ftco-animate" style="align: center">

				<div class="row justify-content-center">
					<div
						class="col-md-12 heading-section text-center ftco-animate mb-5">
						<span class="subheading">OUR BOOK</span>
						<h2 class="mb-2">Choose This Book</h2>
					</div>
					<table style="background: #f8f9fa">
						<tr>
							<td style="padding-left: 20px; text-align: center"><img
								src="${share.book.imageUrl}" alt="Image placeholder"
								class="img-fluid mb-4"></td>
							<td style="padding: 30px; width: 500px">
								<div class="desc">
									<h4>${share.book.name}</h4>
									<p>${share.book.author}(지은이)
										| ${share.book.publisher} |
										<fmt:parseDate value="${share.book.date}" pattern="yyyyMMdd"
											var="parseDate" />
										<fmt:formatDate value="${parseDate}" pattern="yyyy년MM월dd일" />
									</p>
									<p>
										상태 : <span style="text-weight: bold; color: #d4ca68">${share.book.condition}</span>
									</p>
									<p>
										추첨시간 : <span style="font-size: 20px; color: #d4ca68"><fmt:formatDate
												value="${share.raffleTime}" pattern="yyyy년 MM월 dd일 HH시mm분" /></span>&nbsp;&nbsp;<br>
										<span style="font-size: 13px"> (종료시간 : <span id="end"><fmt:formatDate
													value="${share.endTime}" pattern="yyyy-MM-dd HH:mm" /></span> <c:if
												test="${share.status ne 'CLOSE'}">
	              					&nbsp;&nbsp;(남은시간 : <span id="timer"></span>)
	              				</c:if>)
										</span>
									</p>
									<p>
										나눔 수량 : ${share.shareNumber}&nbsp;&nbsp;<span
											style="font-size: 13px" id="peopleNumber">(신청수 :
											${share.peopleNumber})</span>
									</p>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding: 30px">
								<div class="form-group">
									<label for="message">상세설명</label>
									<c:choose>
										<c:when test="${empty share.book.detail}">
											<textarea name="" id="message" cols="30" rows="10"
												class="form-control" style="font-size: 15px" disabled>내용 없습니다.</textarea>
										</c:when>
										<c:otherwise>
											<textarea name="" id="message" cols="30" rows="10"
												class="form-control" style="font-size: 15px" disabled>${share.book.detail}</textarea>
										</c:otherwise>
									</c:choose>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="row justify-content-center" style="margin: 20px">
					<table>
						<tr style="text-align: center">
							<c:choose>
								<c:when test="${share.status eq 'CLOSE'}">
									<td
										style="padding: 30px; background: #666666; color: white; border-radius: 30px">
										이 책은 나눔이 완료되었습니다.</td>
								</c:when>
								<c:otherwise>
									<td style="padding: 30px">
										<%--  	        					<form:form modelAttribute="applier" action="apply.do" method="post">
	        						<div class="form-group">
	        						<form:hidden path="shareId" value="${share.shareId}" />
	        						<input type="submit" id="submit" value="신청하기" class="btn py-3 px-4 btn-primary" />
	        						</div>
	        					</form:form> --%> <c:if
											test="${share.book.userId ne userSession.user.userId}">
											<input type="button" class="btn py-3 px-4 btn-primary"
												value="신청하기" onclick="postJson()" />
										</c:if> <c:if test="${empty userSession.user.userId}">
											<a href="<c:url value="/user/loginForm.do" />"
												class="btn py-3 px-4 btn-primary"><c:out value="로그인하기" /></a>
										</c:if>
									</td>
									<c:if test="${not empty userSession.user.userId}">
										<td><c:url value="/chatRoom.do" var="chatUrl">
												<c:param name="bookId" value="${share.book.bookId}" />
												<c:param name="sellerId" value="${share.book.userId}" />
											</c:url> <a href="${chatUrl}" class="btn py-3 px-4 btn-primary"><c:out
													value="채팅이동" /></a></td>
									</c:if>
								</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</div>
				<div class="pt-5 mt-5" style="margin-left: 20px">
					<h3 class="mb-5">판매자 정보</h3>
					<ul class="comment-list">
						<li class="comment">
							<div class="vcard bio">
								<img
									src="${pageContext.request.contextPath}/resources/images/person_1.jpg"
									alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>${sellerName}님의도서입니다.</h3>
								평점 : ${avg}점
								<div class="star_rating">
									<c:choose>
										<c:when test="${avg eq '1'}">
											<span class="on">★</span>
											<span>★</span>
											<span>★</span>
											<span>★</span>
											<span>★</span>
										</c:when>
										<c:when test="${avg eq '2'}">
											<span class="on">★</span>
											<span class="on">★</span>
											<span>★</span>
											<span>★</span>
											<span>★</span>
										</c:when>
										<c:when test="${avg eq '3'}">
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
											<span>★</span>
											<span>★</span>
										</c:when>
										<c:when test="${avg eq '4'}">
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
											<span>★</span>
										</c:when>
										<c:when test="${avg eq '5'}">
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
											<span class="on">★</span>
										</c:when>
										<c:otherwise>
											<span>★</span>
											<span>★</span>
											<span>★</span>
											<span>★</span>
											<span>★</span>
										</c:otherwise>
									</c:choose>
								</div>

								<p>
									최근 리뷰&nbsp;&nbsp;<a href="javascript:openReview();"
										class="reply">Reply</a>
								</p>

								<table class="table" style="text-align: center;">
									<tbody style="display: none" id="review">
										<tr>
											<th>순번</th>
											<th>별점을 남긴 사용자</th>
											<th>별점</th>
											<th>리뷰 내용</th>
										</tr>
										<c:choose>
											<c:when test="${fn:length(lately) == 0}">
												<tr>
													<td colspan="4">남겨진 리뷰가 없습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach var="r" items="${lately}" varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${r.raterId}</td>
														<td>${r.rate}</td>
														<td>${r.description}</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<!-- .col-md-8 -->
		</div>
	</div>
	</section>
	<!-- .section -->

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
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Search
								Properties</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>For
								Agents</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Reviews</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>FAQs</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md">
				<div class="ftco-footer-widget mb-4 ml-md-4">
					<h2 class="ftco-heading-2">About Us</h2>
					<ul class="list-unstyled">
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Our
								Story</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Meet
								the team</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Careers</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md">
				<div class="ftco-footer-widget mb-4">
					<h2 class="ftco-heading-2">Company</h2>
					<ul class="list-unstyled">
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>About
								Us</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Press</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Contact</a></li>
						<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Careers</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md">
				<div class="ftco-footer-widget mb-4">
					<h2 class="ftco-heading-2">Have a Questions?</h2>
					<div class="block-23 mb-3">
						<ul>
							<li><span class="icon icon-map-marker"></span><span
								class="text">203 Fake St. Mountain View, San Francisco,
									California, USA</span></li>
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
					<script>document.write(new Date().getFullYear());</script>
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
		<svg class="circular" width="48px" height="48px">
		<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
			stroke-width="4" stroke="#eeeeee" />
		<circle class="path" cx="24" cy="24" r="22" fill="none"
			stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>



	<!--  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
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
	<!-- 
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
   -->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

	<script>
	 
	 $(document).ready(function(){
		  tid=setInterval('msg_time()',1000); // 타이머 1초간격으로 수행
	});

	var val = document.getElementById('end').innerHTML;
	val = val.replace(/-/g," ");
	var edDate = new Date(val).getTime();
	var stDate = new Date().getTime();
	var RemainDate = edDate - stDate;
	 
	function msg_time() {
	  var hours = Math.floor((RemainDate % (1000 * 60 * 60 * 24)) / (1000*60*60));
	  var miniutes = Math.floor((RemainDate % (1000 * 60 * 60)) / (1000*60));
	  var seconds = Math.floor((RemainDate % (1000 * 60)) / 1000);
	  
	  m = hours + ":" +  miniutes + ":" + seconds ; // 남은 시간 text형태로 변경
	  
	  document.all.timer.innerHTML = m;   // div 영역에 보여줌 
	  
	  if (RemainDate < 0) {      
	    // 시간이 종료 되었으면..
	    clearInterval(tid);   // 타이머 해제
	    document.all.timer.innerHTML = "00:00:00";
	    location.reload(true);
	  }else{
	    RemainDate = RemainDate - 1000; // 남은시간 -1초
	  }
	}
	</script>
</body>
</html>