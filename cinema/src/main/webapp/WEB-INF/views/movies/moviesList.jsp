<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
 <style>

    .star_grade span{
        text-decoration: none;
        color: black;
    }
    .star_grade span.on{
        color: red;
    }
    
    .check{
        text-decoration:none;
        color: red;
    }   
    .nocheck{
	text-decoration:none;
	    color: black;
	}

</style>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<script>
$(function(){
	  
	var size ='${newMoviesList.size()}'; //평점이 들어있는 ratingList 만큼 반복 
	  for(i=0; i<size ;i++){
	  var rating_value =$('.rating').eq(i).text(); //평점을 가져온다
	  rating_value *= 1; //숫자로 변경
  $('.star_grade span').slice((i*5),rating_value+(i*5)).addClass("on");}
	  
	
	  if("${movies_status}"=="상영중"){
		  $("#상영중").attr('class','check');		  
	  }
	  else{
		  $("#개봉예정").attr('class','check');	
	  }
	 
});

</script>
</head>
<body>
	
<a id="상영중" class="nocheck" href="${contextPath}/movies/moviesList.do?movies_status=상영중">상영중</a>
<a id="개봉예정" class="nocheck" href="${contextPath}/movies/moviesList.do?movies_status=개봉예정">개봉예정</a>

 
     <c:forEach var="item" items="${newMoviesList}">
     <TABLE class="list_view">
			 	 <tr><td rowspan="6">
			 	 <a href="${contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }">
				 <img width="102" height="148" 
				     src="${contextPath}/thumbnails.do?movies_id=${item.movies_id}&fileName=${item.movies_fileName}"></a>   
				 </td></tr>   
				<tr><TD class="no-underline_black">
				     <strong>${item.movies_age_restriction }</strong>
				 <a href="${pageContext.request.contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }"><strong>${item.movies_title } </strong></a>	  
				</TD></tr>
				<tr><td><p><span class="star_grade">						    		       
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>       				
				       </span>
				<span class="rating">${item.movies_rating}</span></p></td></tr>
				<tr><TD>
				   <strong>개요 ${item.movies_genre }|${item.movies_running_time}분|${item.movies_playdate}개봉</strong> 
				</TD></tr>				
				<tr><td>
				 <strong>감독 ${item.movies_director }</strong> 
				</td></tr>
				<tr><td>
				  <strong>출연 ${item.movies_cast }</strong>   			
				</td></tr>
	  </TABLE>

	</c:forEach>

	<br><br><br>


</body>
</html>