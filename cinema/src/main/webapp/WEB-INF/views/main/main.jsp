<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
.main-ground{
    margin: -40px -50px;
 
}


/* Slideshow container */
.slideshow-container {
  padding: 40px 50px; 
  height: 400px;
  position: relative;
  margin: auto;
  background-color: black;

}
.slideshow-container .mySlides img {
  height: 400px;
}

/* Hide the images by default */
.mySlides {
  display: none;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 40%;
  width: auto;
  margin-top: -22px;
  padding: 16px;
  color: #E9EAF0;
  font-weight: bold;
  font-size: 60px;
  font-weight:lighter;
  transition: 0.6s ease; 
  user-select: none;
}

.prev { 
  left: -10px;
}

.next { 
  right: -10px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  color: #F2B950;
}



/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  position:relative;
  top: -30px;
  height: 12px;
  width: 12px;
  margin: 0 1px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.5s ease;
}

.active, .dot:hover {
  background-color: #F2B950;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4}
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4}
  to {opacity: 1}
}



.main_movie .movie {
	background-color: #4F4B4C;
	padding: 8px 0px 0px;
	width: 186px;
	height: 256px;
	text-align: center;
	float: left;
}

.movie .ranking {
  color: white;
  font-size: 35px;  
  position: relative;
  top: -255px;
  left: -70px;

}

.movie .value {
	background-color: #4F4B4C;
	color: white;
	padding: 0px 12px;
	font-size: 18px;  
	position: relative;
	top: -50px;
	text-align: left;
}


</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	
		var slideIndex = 0; //slide index

		// HTML 로드가 끝난 후 동작
		window.onload=function(){
		  showSlides(slideIndex);

		  // Auto Move Slide
		  var sec = 10000;
		  setInterval(function(){
		    slideIndex++;
		    showSlides(slideIndex);

		  }, sec);
		}

		// Next/previous controls
		function moveSlides(n) {
		  slideIndex = slideIndex + n
		  showSlides(slideIndex);
		}

		// Thumbnail image controls
		function currentSlide(n) {
		  slideIndex = n;
		  showSlides(slideIndex);
		}

		function showSlides(n) {

		  var slides = document.getElementsByClassName("mySlides");
		  var dots = document.getElementsByClassName("dot");
		  var size = slides.length;

		  if ((n+1) > size) {
		    slideIndex = 0; n = 0;
		  }else if (n < 0) {
		    slideIndex = (size-1);
		    n = (size-1);
		  }

		  for (i = 0; i < slides.length; i++) {
		      slides[i].style.display = "none";
		  }
		  for (i = 0; i < dots.length; i++) {
		      dots[i].className = dots[i].className.replace(" active", "");
		  }

		  slides[n].style.display = "block";
		  dots[n].className += " active";
		}



		$(function(){
			  
			  var size ='${moviesMap.onscreen.size()}'; 
			  for(i=0; i<size ;i++){
			  $('.main_movie .ranking').eq(i).text(i+1); }			
			 
		});



</script>
</head>
<body>
<div class="main-ground">
	<div class="slideshow-container">

      <!-- Full-width images with number and caption text -->
      <div class="mySlides fade">   
        <img src="${contextPath}/resources/image/event1.png" style="width:100%">
      </div>

      <div class="mySlides fade">
        <img src="${contextPath}/resources/image/event2.png" style="width:100%">      
      </div>

      <div class="mySlides fade">
        <img src="${contextPath}/resources/image/event3.png" style="width:100%">       
      </div>

      <div class="mySlides fade">
        <img src="${contextPath}/resources/image/event4.png" style="width:100%">    
      </div>

      

      <!-- Next and previous buttons -->
      <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
      <a class="next" onclick="moveSlides(1)">&#10095;</a>
       <!-- The dots/circles -->
    <div style="text-align:center">
      <span class="dot" onclick="currentSlide(0)"></span>
      <span class="dot" onclick="currentSlide(1)"></span>
      <span class="dot" onclick="currentSlide(2)"></span>
      <span class="dot" onclick="currentSlide(3)"></span>
    
    </div>
    </div>
    <br/>
</div>

	<div class="main_movie">
	    <br/><h2>&nbsp;무비차트</h2>
		<c:set var="movies_count" value="0" />	
		<c:forEach var="item" items="${moviesMap.onscreen}">

			<div class="movie">
			
				<a	href="${contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }">
					<img width="170" height="246"
					src="${contextPath}/thumbnails.do?movies_id=${item.movies_id}&fileName=${item.movies_fileName}">
				</a>
				<div class="ranking"></div>
				<div class="value">예매율 &nbsp;${item.reservation_rate}%</div>
				
			</div>

		</c:forEach>
	</div>
</body>
</html>