<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>주섬주섬</title>
    <meta charset="utf-8">
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
	
	<!-- 시간 입력 form을 위한 link -->
	<link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet"/>
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	<!-- select box  -->
	<style>
		.selectbox {
			background: #fff;
			border-radius: 5px;
		    position: relative;
		    width: 200px;  /* 너비설정 */
		    border: 1px solid #ced4da;  /* 테두리 설정 */
		    z-index: 1;
		}
		
		/* 가상 선택자를 활용 화살표 대체 */
		.selectbox:before {
		    content: "";
		    position: absolute;
		    top: 50%;
		    right: 15px;
		    width: 0;
		    height: 0;
		    margin-top: -1px;
		    border-left: 5px solid transparent;
		    border-right: 5px solid transparent;
		    border-top: 5px solid #333;
		}
		
		.selectbox label {
		    position: absolute;
		    top: 1px;  /* 위치정렬 */
		    left: 5px;  /* 위치정렬 */
		    padding: .8em .5em;  /* select의 여백 크기 만큼 */
		    color: #999;
		    z-index: -1;  /* IE8에서 label이 위치한 곳이 클릭되지 않는 것 해결 */
		}

		.selectbox select {
		    width: 100%;
		    height: auto;  /* 높이 초기화 */
		    line-height: normal;  /* line-height 초기화 */
		    font-family: inherit;  /* 폰트 상속 */
		    padding: .8em .5em;  /* 여백과 높이 결정 */
		    border: 0;
		    opacity: 0;  /* 숨기기 */
		    filter:alpha(opacity=0);  /* IE8 숨기기 */
		    -webkit-appearance: none; /* 네이티브 외형 감추기 */
		    -moz-appearance: none;
		    appearance: none;
		}
	</style>
	<!-- select box  -->
	<script>
	$(function() {
	    var selectTarget = $('.selectbox select');

	    selectTarget.change(function(){
	        var select_name = $(this).children('option:selected').text();
	        $(this).siblings('label').text(select_name);
	    });
	});
	</script>
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
    
    <div class="hero-wrap" style="background-image: url('${pageContext.request.contextPath}/resources/images/bg_test8.jpg');" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row no-gutters slider-text justify-content-center align-items-center">
          <div class="col-lg-8 col-md-6 ftco-animate d-flex align-items-end">
          	<div class="text text-center w-100">
	            <h1 class="mb-4">Sell Your Book<br>That Make You Money</h1>
            </div>
          </div>
        </div>
      </div>
      <div class="mouse">
				<a href="#" class="mouse-icon">
					<div class="mouse-wheel"><span class="ion-ios-arrow-round-down"></span></div>
				</a>
			</div>
    </div>

	<!-- 선택한 도서 -->
    <section class="ftco-section ftco-property-details">
      <div class="container">
      	<div class="row justify-content-center">
          	<div class="col-md-12 heading-section text-center ftco-animate mb-5">
	          	<span class="subheading">Let's Share And Change</span>
	            <h2 class="mb-2">The Book Of Your Choice</h2>
          	</div>
        </div>
      	<div class="row justify-content-center">
      		<div class="col-md-4">
	        	<div class="property-wrap ftco-animate">
					<c:choose>				
					
					<c:when test="${!empty bookRegi}">
					<div class="img d-flex align-items-center justify-content-center" style="background-image: url(${bookRegi.imageUrl});">
	        		</div>
	        		<div class="text" style="border: 1px solid rgba(0, 0, 0, 0.1); border-radius: 4px">
	        			<h3>${bookRegi.name}</h3>
	        			<table style="font-size: 14px">
	        				<tr>
	        					<td>저자</td>
	        					<td style="padding-left: 20px">${bookRegi.author}</td>
	        				</tr>
	        				<tr>
	        					<td>출판사</td>
	        					<td style="padding-left: 20px">${bookRegi.publisher}</td>
	        				</tr>
	        				<tr>
	        					<td>출판일</td>
	        					<td style="padding-left: 20px">
	        						<fmt:parseDate value="${bookRegi.date}" pattern="yyyyMMdd" var="parseDate"/>
	        						<fmt:formatDate value="${parseDate}" pattern="yyyy년MM월dd일" />
	        					</td>
	        				</tr>
	        				<tr>
	        					<td>정가</td>
	        					<td style="padding-left: 20px"><fmt:formatNumber value="${bookRegi.price}" pattern="#,###"/>원</td>
	        				</tr>
	        			</table>
	        		</div>
					</c:when>
					
					<c:otherwise>
					<div class="img d-flex align-items-center justify-content-center" style="background-image: url(${book.imageUrl});">
	        		</div>
	        		<div class="text" style="border: 1px solid rgba(0, 0, 0, 0.1); border-radius: 4px">
	        			<h3>${bookRegi.name}</h3>
	        			<table style="font-size: 14px">
	        				<tr>
	        					<td>저자</td>
	        					<td style="padding-left: 20px">${book.author}</td>
	        				</tr>
	        				<tr>
	        					<td>출판사</td>
	        					<td style="padding-left: 20px">${book.publisher}</td>
	        				</tr>
	        				<tr>
	        					<td>출판일</td>
	        					<td style="padding-left: 20px">
	        						<fmt:parseDate value="${book.date}" pattern="yyyyMMdd" var="parseDate"/>
	        						<fmt:formatDate value="${parseDate}" pattern="yyyy년MM월dd일" />
	        					</td>
	        				</tr>
	        				<tr>
	        					<td>정가</td>
	        					<td style="padding-left: 20px"><fmt:formatNumber value="${book.price}" pattern="#,###"/>원</td>
	        				</tr>
	        			</table>
	        		</div>
					</c:otherwise>
					
					</c:choose>
					
        		</div>
        	</div>
      		<div class="col-md-12">
      			<div class="property-details">
      				<div class="text">
      					<h5>자세한 책 정보를 적어주세요.</h5>
      					<span class="subheading">판매분류</span>
      				</div>
      			</div>
      		</div>
      	</div>
      	<!-- 입력  -->
      	<div class="row">
      		<div class="col-md-12 pills">
						<div class="bd-example bd-example-tabs">
							<div class="d-flex">
							  <ul class="nav nav-pills mb-2" id="pills-tab" role="tablist">
							    <li class="nav-item">
							      <c:choose>
								  <c:when test="${empty s_choice and empty a_choice and empty sh_choice}">
							      	<a class="nav-link active" id="pills-description-tab" data-toggle="pill" href="#pills-description" role="tab" aria-controls="pills-description" aria-expanded="true">중고거래</a>
							      </c:when>
							      <c:when test="${!empty s_choice}">
							      	<a class="nav-link active" id="pills-description-tab" data-toggle="pill" href="#pills-description" role="tab" aria-controls="pills-description" aria-expanded="true">중고거래</a>
							      </c:when>
							      <c:otherwise>
							      	<a class="nav-link" id="pills-description-tab" data-toggle="pill" href="#pills-description" role="tab" aria-controls="pills-description" aria-expanded="true">중고거래</a>
							      </c:otherwise>
							      </c:choose>
							    </li>
							    <li class="nav-item">
							    <c:choose>
								<c:when test="${!empty a_choice}">
							    	<a class="nav-link active" id="pills-manufacturer-tab" data-toggle="pill" href="#pills-manufacturer" role="tab" aria-controls="pills-manufacturer" aria-expanded="true">경매</a>
							    </c:when>
								<c:otherwise>
									<a class="nav-link" id="pills-manufacturer-tab" data-toggle="pill" href="#pills-manufacturer" role="tab" aria-controls="pills-manufacturer" aria-expanded="true">경매</a>							    
								</c:otherwise>
							    </c:choose>
							    </li>
							    <li class="nav-item">
							    <c:choose>
								<c:when test="${!empty sh_choice}">
							      <a class="nav-link active" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-expanded="true">나눔</a>
							    </c:when>
							    <c:otherwise>
							      <a class="nav-link" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-expanded="true">나눔</a>
								</c:otherwise>
							    </c:choose>
							    </li>
							  </ul>
							</div>

						  <div class="tab-content" id="pills-tabContent">
						  	<!-- 첫번째 탭 -->
						  	<c:choose>
							<c:when test="${empty s_choice and empty a_choice and empty sh_choice}">
								<div class="tab-pane fade show active" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab">
							</c:when>
							<c:when test="${!empty s_choice}">
								<div class="tab-pane fade show active" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab">
							</c:when>
							<c:otherwise>
								<div class="tab-pane fade" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab">
							</c:otherwise>
							</c:choose>
						    <!--  <div class="tab-pane fade show active" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab"> -->
						    	<form:form modelAttribute="sale" action="sale.do" class="bg-light p-5 contact-form" style="margin-left: 100px; margin-right: 100px">
									<div class="form-group">
										<label for="name">상태선택</label>
										<div class="selectbox">
											<label for="ex_select">${sale.book.condition}</label>
											<form:select path="book.condition" id="ex_select">
												<form:options items="${conditionCodes}"/>
											</form:select>
										</div>
									</div>
				            		<div class="form-group">
				             			<label for="price">희망가격 *</label>
				             			<form:input path="suggestPrice" class="form-control" id="price"/>
										<form:errors path="suggestPrice"/><br/>
				             		</div>
				             		<div class="form-group">
				                    	<label for="message">세부사항</label>
				                    	<form:textarea path="book.detail" cols="30" rows="10" class="form-control" id="message"/>
				                    	<form:errors path="book.detail"/><br/>
				                  	</div>
				                  	<form:hidden path="book.imageUrl" value="${bookRegi.imageUrl}"/>
				                  	<form:hidden path="book.name" value="${bookRegi.name}"/>
				                  	<form:hidden path="book.author" value="${bookRegi.author}"/>
				                  	<form:hidden path="book.publisher" value="${bookRegi.publisher}"/>
				                  	<form:hidden path="book.date" value="${bookRegi.date}"/>
				                  	<form:hidden path="book.price" value="${bookRegi.price}"/>
				              		<div class="form-group">
				                		<input type="submit" value="등록하기" class="btn btn-primary py-3 px-5">
				              		</div>				
            					</form:form> 
						    </div>
							<!-- 두번째 탭 -->
							<c:choose>
							<c:when test="${!empty a_choice}">
						    	<div class="tab-pane fade show active" id="pills-manufacturer" role="tabpanel" aria-labelledby="pills-manufacturer-tab">
							</c:when>
							<c:otherwise>
								<div class="tab-pane fade" id="pills-manufacturer" role="tabpanel" aria-labelledby="pills-manufacturer-tab">
							</c:otherwise>
							</c:choose>
						    <!--  <div class="tab-pane fade" id="pills-manufacturer" role="tabpanel" aria-labelledby="pills-manufacturer-tab"> -->
						      <form:form modelAttribute="auction" action="auction.do" class="bg-light p-5 contact-form" style="margin-left: 100px; margin-right: 100px">
				            		<div class="form-group">
										<label for="name">상태선택</label>
										<div class="selectbox">
											<label for="ex_select">${auction.book.condition}</label>
											<form:select path="book.condition" id="ex_select">
												<form:options items="${conditionCodes}"/>
											</form:select>
										</div>
									</div>
				            		<div class="form-group">
				             			<label for="price">시작가격 *</label>
				             			<form:input path="startPrice" class="form-control" id="price"/>
										<form:errors path="startPrice"/><br/>
				             		</div>
				             		<div class="form-group">
				             			<label for="quantity">총판매수랑 *</label>
				             			<form:input path="salesNumber" class="form-control" id="quantity"/>
										<form:errors path="salesNumber"/><br/>
				             		</div>
				             		<div class="form-group">
				             			<label for="endtime">종료시간 *</label>
				             		
										<div class="input-group date" id="datetimepicker7">
											<form:input path="endTime" class="form-control" id="endtime" autocomplete="off"/>
										    <span class="input-group-addon" style="display: inline-flex; min-width: 56px; max-width: 82px; width: 33.3%; height: 52px; align-items: center; justify-content: center;" >
										    	<span class="icon-calendar mr-2" ></span>
										    </span>
										    <script>
										    $('#datetimepicker7').datetimepicker({
											    sideBySide: true,
											    icons: {
											        up: "fa fa-chevron-circle-up",
											        down: "fa fa-chevron-circle-down",
											        next: 'fa fa-chevron-circle-right',
											        previous: 'fa fa-chevron-circle-left'
											    }
											});
										    </script>
										</div>
										<form:errors path="endTime"/><br/>
				             		</div>
				             		<div class="form-group">
				                    	<label for="message">세부사항</label>
				                    	<form:textarea path="book.detail" cols="30" rows="10" class="form-control" id="message"/>
				                    	<form:errors path="book.detail"/><br/>
				                  	</div>
				                  	<form:hidden path="book.imageUrl" value="${bookRegi.imageUrl}"/>
				                  	<form:hidden path="book.name" value="${bookRegi.name}"/>
				                  	<form:hidden path="book.author" value="${bookRegi.author}"/>
				                  	<form:hidden path="book.publisher" value="${bookRegi.publisher}"/>
				                  	<form:hidden path="book.date" value="${bookRegi.date}"/>
				                  	<form:hidden path="book.price" value="${bookRegi.price}"/>
				              		<div class="form-group">
				                		<input type="submit" value="등록하기" class="btn btn-primary py-3 px-5">
				              		</div>
            					</form:form> 
						    </div>
							<c:choose>
							<c:when test="${!empty sh_choice}">
						    	<div class="tab-pane fade show active" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
						    </c:when>
						    <c:otherwise>
						    	<div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
						    </c:otherwise>
						    </c:choose>
						      <form:form modelAttribute="share" action="share.do" class="bg-light p-5 contact-form" style="margin-left: 100px; margin-right: 100px">
						      		<div class="form-group">
										<label for="name">상태선택</label>
										<div class="selectbox">
											<label for="ex_select">${share.book.condition}</label>
											<form:select path="book.condition" id="ex_select">
												<form:options items="${conditionCodes}"/>
											</form:select>
										</div>
									</div>
				            		<div class="form-group">
				             			<label for="shareNumber">나눔인원 *</label>
                    					<form:input path="shareNumber" class="form-control" id="shareNumber"/>
										<form:errors path="shareNumber"/><br/>
				             		</div>
				             		<div class="form-group">
				             			<label for="endTime">종료시간 *</label>
                    					<div class="input-group date" id="datetimepicker8">
											<form:input path="endTime" class="form-control" id="endtime" autocomplete="off"/>
										    <span class="input-group-addon" style="display: inline-flex; min-width: 56px; max-width: 82px; width: 33.3%; height: 52px; align-items: center; justify-content: center;" >
										    	<span class="icon-calendar mr-2" ></span>
										    </span>
										    <script>
											    $('#datetimepicker8').datetimepicker({
												    sideBySide: true,
												    icons: {
												        up: "fa fa-chevron-circle-up",
												        down: "fa fa-chevron-circle-down",
												        next: 'fa fa-chevron-circle-right',
												        previous: 'fa fa-chevron-circle-left'
												    }
												});
										    </script>
										</div>
										<form:errors path="endTime"/><br/>
				             		</div>
				             		<div class="form-group">
				             			<label for="raffleTime">추첨시간 *</label>
                    					<div class="input-group date" id="datetimepicker9">
											<form:input path="raffleTime" class="form-control" id="raffleTime" autocomplete="off"/>
										    <span class="input-group-addon" style="display: inline-flex; min-width: 56px; max-width: 82px; width: 33.3%; height: 52px; align-items: center; justify-content: center;" >
										    	<span class="icon-calendar mr-2" ></span>
										    </span>
										    <script>
											    $('#datetimepicker9').datetimepicker({
												    sideBySide: true,
												    icons: {
												        up: "fa fa-chevron-circle-up",
												        down: "fa fa-chevron-circle-down",
												        next: 'fa fa-chevron-circle-right',
												        previous: 'fa fa-chevron-circle-left'
												    }
												});
										    </script>
										   </div>
										   <form:errors path="raffleTime"/><br/>
				             		</div>
				             		<div class="form-group">
				                    	<label for="message">세부사항</label>
				                    	<form:textarea path="book.detail" cols="30" rows="10" class="form-control" id="message"/>
				                    	<form:errors path="book.detail"/><br/>
				                  	</div>
				                  	<form:hidden path="book.imageUrl" value="${bookRegi.imageUrl}"/>
				                  	<form:hidden path="book.name" value="${bookRegi.name}"/>
				                  	<form:hidden path="book.author" value="${bookRegi.author}"/>
				                  	<form:hidden path="book.publisher" value="${bookRegi.publisher}"/>
				                  	<form:hidden path="book.date" value="${bookRegi.date}"/>
				                  	<form:hidden path="book.price" value="${bookRegi.price}"/>
				              		<div class="form-group">
				                		<input type="submit" value="등록하기" class="btn btn-primary py-3 px-5">
				              		</div>
            					</form:form> 
						    </div>
						  </div>
						</div>
		      </div>
			</div>
      </div>
    </section>

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