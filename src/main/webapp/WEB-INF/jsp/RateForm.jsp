<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>별점 입력하는 form</title>

	 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	 
	 <style>
		.rate {
		  float: left;
		  border: none;
		}
		.rate:not(:checked) > input {
		  position: absolute;
		  top: -9999px;
		  clip: rect(0, 0, 0, 0);
		}
		.rate:not(:checked) > label {
		  float: right;
		  width: 1em;
		  padding: 0 .1em;
		  overflow: hidden;
		  white-space: nowrap;
		  cursor: pointer;
		  font-size: 200%;
		  line-height: 1.2;
		  color: #ddd;
		}
		.rate:not(:checked) > label:before {
		  content: '★ ';
		}
		.rate > input:checked ~ label {
		  color: #f70;
		}
		.rate:not(:checked) > label:hover,
		.rate:not(:checked) > label:hover ~ label {
		  color: gold;
		}
		.rate > input:checked + label:hover,
		.rate > input:checked + label:hover ~ label,
		.rate > input:checked ~ label:hover,
		.rate > input:checked ~ label:hover ~ label,
		.rate > label:hover ~ input:checked ~ label {
		  color: #ea0;
		}
		.rate > label:active {
		  position: relative;
		}
	</style>
	 
	<script>
	$('.rating input').change(function() {
		$('#choice').text(this.value + ' stars');
		});

    </script>
</head>

<body>

<form:form modelAttribute="rate" method="post" action="rate.do">
	<div class="form-group">
		<label for="rate">qwerty에게 별점 남기기</label>
		<br/>
		<fieldset class="rate">
			<input type="radio" id="star5" name="rate" value="5" />
			<label for="star5">5 stars</label>
			<input type="radio" id="star4" name="rate" value="4" />
			<label for="star4">4 stars</label>
			<input type="radio" id="star3" name="rate" value="3" />
			<label for="star3">3 stars</label>
			<input type="radio" id="star2" name="rate" value="2" />
			<label for="star2">2 stars</label>
			<input type="radio" id="star1" name="rate" value="1" />
			<label for="star1">1 star</label>
		</fieldset>
		<br/>
		<br/>
		<br/>
	</div>
	<div class="form-group">
		<form:label path="description">한줄 리뷰</form:label>
		<form:textarea path="description" cols="20" rows="2"/>
		<form:errors path="description" /> <br/>
	</div>	

	<form:hidden path="raterId" value="test" /> <!-- 현재 사용자 (남기는 사람) -->
	<form:hidden path="ratedId" value="qwerty" /> <!-- 받는 사람 -->
	<input type="submit" value="SEND"/>
</form:form>
</body>
</html>