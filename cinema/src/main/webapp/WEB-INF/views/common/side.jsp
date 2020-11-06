<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>

  <meta charset="UTF-8">
  <title>메뉴</title>
</head>
 <style>

    .side_select{
        text-decoration:none;
        color: #EA426C;
    }
    
</style>

<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<script>
$(function(){
	  
	
	  if("${side_status}"=="moviesList"){
		  $(".user a:contains('영화')").attr('class','side_select');		  
	  }
	  else if("${side_status}"=="moviesRanking"){
		  $(".user a:contains('랭킹')").attr('class','side_select');				  
	  }
	  else if("${side_status}"=="choiceScheduleForm"){
		  $(".user a:contains('예매')").attr('class','side_select');				  
	  }
	  else if("${side_status}"=="listMyReservationHistory"){
		  $(".mypage a:contains('예매내역')").attr('class','side_select');				  
	  }
	  else if("${side_status}"=="myDetailInfo"){
		  $(".mypage a:contains('회원정보')").attr('class','side_select');				  
	  }
	  else if("${side_status}"=="adminMoviesMain"){
		  $(".admin a:contains('영화관리')").attr('class','side_select');				  
	  }
	  else if("${side_status}"=="adminScheduleMain"){
		  $(".admin a:contains('상영관리')").attr('class','side_select');				  
	  }
	 
});
</script>

<body >

	
<nav class="side_menu">

<h1> <a href="${contextPath}/main/main.do" class="no-underline_pely" >PELY</a> </h1>

<c:choose>
<c:when test="${side_menu=='admin_mode' }">
    <h2 class="admin">
	    <a href="${contextPath}/admin/movies/adminMoviesMain.do" >영화관리</a><br>
	    <a href="${contextPath}/admin/schedule/adminScheduleMain.do">상영관리</a><br>
	</h2>
</c:when>
<c:when test="${side_menu=='my_page' }">
	<h2 class="mypage">
	    <a href="${contextPath}/mypage/listMyReservationHistory.do">예매내역</a>
		<a href="${contextPath}/mypage/myDetailInfo.do">회원정보</a>
		<a href="#">회원탈퇴</a>
	</h2>
</c:when>
<c:otherwise>
	<h2 class="user">
		<a href="${contextPath}/movies/moviesList.do?movies_status=상영중">영화</a><br>
		<a href="${contextPath}/movies/moviesRanking.do">랭킹</a><br>
	    <a href="${contextPath}/reservation/choiceScheduleForm.do" >예매</a><br>
	    <a href="#">이벤트</a><br>
	    <br>
	    <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">  
	   	   <a href="${contextPath}/admin/movies/adminMoviesMain.do">관리자</a>
	    </c:if>
    </h2>
 </c:otherwise>
</c:choose>	
</nav>
	
</body>
</html>