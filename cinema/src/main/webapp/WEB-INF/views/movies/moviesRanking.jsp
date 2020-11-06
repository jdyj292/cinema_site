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

</style>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<script>
$(function(){
	  
	  var size ='${newMoviesList.size()}'; //평점이 들어있는 ratingList 만큼 반복 
	  for(i=0; i<size ;i++){
	  var rating_value =$('.rating').eq(i).text(); //평점을 가져온다
	  rating_value *= 1; //숫자로 변경
    $('.star_grade span').slice((i*5),rating_value+(i*5)).addClass("on");
	  
    $('.rank').eq(i).text(i+1);	  
	  }
});

</script>
</head>
<body>
	

 
   
     <TABLE class="list_view">
             <tr>
				<td>순위</td>
				<td>이름</td>
				<td>평점</td>
			</tr>
         <c:forEach var="item" items="${newMoviesList}">
                <tr>
                <td class="rank"></td>
				<TD class="no-underline_black">
				     <strong>${item.movies_age_restriction }</strong>
				 <a href="${pageContext.request.contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }"><strong>${item.movies_title } </strong></a>	  
				</TD>
				<td><p><span class="star_grade">						    		       
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>
        				<span>★</span>       				
				       </span>
				<span class="rating">${item.movies_rating}</span></p></td>
				</tr>
			
	      </c:forEach>
				
	  </TABLE>

	<br><br><br>


</body>
</html>