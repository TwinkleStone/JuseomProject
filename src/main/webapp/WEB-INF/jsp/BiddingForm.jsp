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
	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<c:url value="/index.do" />">주섬주섬</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="<c:url value="/index.do" />" class="nav-link">중고</a></li>
	          <li class="nav-item"><a href="<c:url value="/auction.do" />" class="nav-link">경매</a></li>
	          <li class="nav-item"><a href="<c:url value="/share.do" />" class="nav-link">나눔</a></li>
	          <li class="nav-item active"><a class="nav-link" href="<c:url value="/insert/search.do" />">등록</a></li>
	          <!--  <li class="nav-item"><a href="properties.html" class="nav-link">Listing</a></li>
	          <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li> -->
	        </ul>
	      </div>
	    </div>
	</nav>
	<!-- END nav -->
	
	<div class="hero-wrap" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_5.jpg');" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row no-gutters slider-text justify-content-center align-items-center">
          
        </div>
      </div>
    </div>
	
	<div class="container">
	 <div class="row justify-content-center">
	   <div class="co-md-4" style="padding: 30px">
		<form:form modelAttribute="bidder" action="bidding.do" method="post" class="bg-light p-5 contact-form">
			<div class="form-group" style="width: 500px">
				<label for="bidNumber">입찰할 책의 갯수 *</label>
				<form:input path="bidNumber" class="form-control"/> <!-- 왜 input창에 값이 안써지는겨;;??!! -->
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
	
	<footer class="ftco-footer ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Findstate</h2>
              <p>Far far away, behind the word mountains, far from the countries.</p>
              <ul class="ftco-footer-social list-unstyled mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-4">
              <h2 class="ftco-heading-2">Community</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Search Properties</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>For Agents</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Reviews</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>FAQs</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-4">
              <h2 class="ftco-heading-2">About Us</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Our Story</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Meet the team</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Careers</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Company</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>About Us</a></li>
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
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope pr-4"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
	
            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

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
</html>