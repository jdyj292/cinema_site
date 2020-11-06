<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="movies" value="${moviesMap.moviesVO}" />
<c:set var="imageList" value="${moviesMap.imageList }" />

<%
	request.setCharacterEncoding("UTF-8");
	//치환 변수 선언합니다.
	pageContext.setAttribute("crcn", "\n"); //Space, Enter
	pageContext.setAttribute("br", "<br/>"); //br 태그
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
.main-ground {
	width: 810px;
	margin: -40px -50px;
	position: relative;
	top: 30px;
}

.sub-ground {
	background-color: gray;
	width: 700px;
	height: 100px;
}

/* Slideshow container */
.slideshow-container {
	padding: 40px 50px;
	height: 400px;
	position: relative;
	margin: auto;
}

.slideshow-container .mySlides img {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
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
	color: gray;
	font-weight: bold;
	font-size: 60px;
	font-weight: lighter;
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
    float: left;
    overflow: hidden;
	cursor: pointer;
	height: 100px;
	width: 100px;
	box-sizing: border-box;
}

.active2, .dot:hover img {
    margin:-3px;
}

.active, .dot:hover {
    border : 3px solid ; 
	border-color: #F2B950;
}



/* Fading animation */
.fade {
	-webkit-animation-name: fade;
	-webkit-animation-duration: 1.5s;
	animation-name: fade;
	animation-duration: 1.5s;
}

@
-webkit-keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}
@
keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}
.main_movie {
	margin: 0px 0px 40px;
}

.main_movie .movie {
	border: 2px solid rgb(255, 255, 255);
	padding: 10px 0px;
	width: 180px;
	height: 256px;
	text-align: center;
	float: right;
}

.main_movie .detail {
	font-size: 20px;
}

.star_grade span {
	text-decoration: none;
	color: black;
}

.star_grade span.on {
	color: red;
}

.star_grade2 span {
	text-decoration: none;
	color: black;
}

.star_grade2 span.on {
	color: red;
}

button {
	background: none;
	border: 0;
	outline: 0;
	cursor: pointer;
}

.tab_menu_container {
	display: flex;
}

.tab_menu_btn {
	width: 100px;
	height: 40px;
}

.tab_menu_btn.on {
	border-bottom: 2px solid #df0000;
	font-weight: 700;
	color: #df0000;
}

.tab_menu_btn:hover {
	color: #df0000;
}

.tab_box {
	display: none;
	padding: 20px;
}

.tab_box.on {
	display: block;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function() {
 
		var size = '${ratingList.size()}' //평점이 들어있는 ratingList 만큼 반복 
		for (i = 0; i < size; i++) {
			var rating_value = $('.star_grade span').eq(5 + i * 6).text(); //평점을 가져온다
			rating_value *= 1; //숫자로 변경
			$('.star_grade span').slice((i * 6), rating_value + (i * 6))
					.addClass("on");

		}

		var round_rating = Math.round('${movies.movies_rating}'); //평균평점
		$('.star_grade2 span').slice(0, round_rating).addClass("on");

		if ("${movies.movies_status}" == "개봉예정") {
			$('input[name=rating_button]').hide();
		}

		$('.tab_menu_btn').on('click', function() {
			//버튼 색 제거,추가
			$('.tab_menu_btn').removeClass('on');
			$(this).addClass('on')

			//컨텐츠 제거 후 인덱스에 맞는 컨텐츠 노출
			var idx = $('.tab_menu_btn').index(this);

			$('.tab_box').hide();
			$('.tab_box').eq(idx).show();
		});
		
		
		var imageNames = "${imageNames}";
		var imageNameList = imageNames.split(" ");
		var image_size = "${imageList.size()}";
		var id ="${movies.movies_id}";
		for(var i=0;i<image_size;i++){ //포토 썸네일 
			$(".sub-ground").append("<span class='dot' onclick='currentSlide("+i+")'><img class='d_image' width='100' height='100' src='${contextPath}/thumbnails.do?movies_id="+id+"&fileName="+imageNameList[i]+"'></span>");
		}
	});

	function move_rating_form() {
		var _movies_id = '${movies.movies_id}';

		$
				.ajax({
					type : "post",
					async : false,
					url : "${contextPath}/movies/checkrating.do",
					dataType : "text",
					data : {
						movies_id : _movies_id
					},
					success : function(data, textStatus) {
						if (data == "needlogin") {
							alert("로그인이 필요합니다.");
							location.href = "${contextPath}/member/loginForm.do";
						} else if (data == "1") {
							alert("이미 평점을 등록하셨습니다");
						} else if (data == "0") {
							location.href = "${contextPath}/movies/addRatingForm.do?movies_title=${movies.movies_title}&movies_id=${movies.movies_id}";
						}
					},
					error : function(data, textStatus) {
						alert("에러가 발생했습니다.");
						ㅣ
					},
					complete : function(data, textStatus) {
						//alert("작업을완료 했습니다");
					}
				}); //end ajax	 
	}
	

    var slideIndex = 0;
	// HTML 로드가 끝난 후 동작
	window.onload=function(){
	  showSlides(slideIndex);
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
	  var d_images = document.getElementsByClassName("d_image");
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
	      d_images[i].className = d_images[i].className.replace(" active2", "");
	  }

	  slides[n].style.display = "block";
	  dots[n].className += " active";
	  d_images[n].className += " active2";
	}



	$(function(){
		  
		  var size ='${moviesMap.onscreen.size()}'; 
		  for(i=0; i<size ;i++){
		  $('.main_movie .ranking').eq(i).text(i+1); }			
		 
	});
</script>
</head>
<body>
	<div class="main_movie">

		<div class="movie">

			<img width="170" height="246"
				src="${contextPath}/thumbnails.do?movies_id=${movies.movies_id}&fileName=${movies.movies_fileName}">
		</div>

		<div class="ditail">

			<table>

				<tr>
					<td colspan="2"><strong style="font-size: 30px;">${movies.movies_title }</strong>
						${movies.movies_status}</td>

				</tr>

				<tr>
					<td><strong>평점</strong></td>
					<td>
						<p class="star_grade2">
							<span>★</span> <span>★</span> <span>★</span> <span>★</span> <span>★</span>
							<span>${movies.movies_rating}</span>
					</td>
				</tr>

				<tr>
					<td><strong>개요</strong></td>
					<td>${movies.movies_genre}|${movies.movies_country}
						|${movies.movies_running_time}분 |${movies.movies_playdate}</td>
				</tr>
				<tr>
					<td><strong>감독</strong></td>
					<td>${movies.movies_director}</td>
				</tr>
				<tr>
					<td><strong>출연</strong></td>
					<td>${movies.movies_cast}</td>
				</tr>
				<tr>
					<td><strong>등급</strong></td>
					<td>[국내]${movies.movies_age_restriction}</td>
				</tr>

				<tr>
					<td><strong>흥행</strong></td>
					<td>누적관객 ${movies.movies_total_attendance}명</td>
				</tr>

				<tr>
					<td><br></td>
				</tr>
			</table>


		</div>
		<div>
			<input type="button"
				onclick="location.href='${contextPath}/reservation/choiceScheduleForm.do?movies_title=${movies.movies_title}'"
				value="예매하기" />
		</div>

		<div class="tab_wrap">
			<div class="tab_menu_container">
				<button class="tab_menu_btn on" type="button">줄거리</button>
				<button class="tab_menu_btn" type="button">배우/제작진</button>
				<button class="tab_menu_btn" type="button">포토</button>
				<button class="tab_menu_btn" type="button">동영상</button>
				<button class="tab_menu_btn" type="button">평점</button>

			</div>
			<!-- tab_menu_container e -->

			<div class="tab_box_container">
				<div class="tab_box on">
					${fn:replace(movies.movies_summary,crcn,br)}</div>
				<div class="tab_box"></div>
				<div class="tab_box">

					<div class="sub-ground">
		
					</div>

					<div class="main-ground">
						<div class="slideshow-container">
							<c:forEach var="image" items="${imageList}">
								<div class="mySlides fade">
									<img
										src="${contextPath}/download.do?movies_id=${movies.movies_id}&fileName=${image.fileName}">
								</div>
							</c:forEach>

							<!-- Next and previous buttons -->
							<a class="prev" onclick="moveSlides(-1)">&#10094;</a> <a
								class="next" onclick="moveSlides(1)">&#10095;</a>

						</div>
					</div>

				</div>
				<div class="tab_box"></div>
				<div class="tab_box">
					<c:choose>
						<c:when test="${movies.movies_status != '개봉예정'}">
							<input type="button" name="rating_button"
								onclick="move_rating_form()" value="평점 등록" />
						</c:when>
						<c:otherwise>
							개봉전 영화는 평점을 등록할 수 없습니다.
						</c:otherwise>
					</c:choose>
					<table>
						<tbody>
							<c:forEach var="item" items="${ratingList}">
								<tr>
									<td><p class="star_grade">
											<span>★</span> <span>★</span> <span>★</span> <span>★</span> <span>★</span>
											<span>${item.rating_value}</span>
										</p></td>
								</tr>
								<tr>
									<td>${item.rating_contend}</td>
								</tr>
								<tr>
									<td>${item.member_id}&nbsp;${item.rating_date}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
			<!-- tab_box_container e -->
		</div>
		<!-- tab_wrap e -->





	</div>
</body>
</html>