<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<script>
	history.pushState(null, null, location.href);
	window.onpopstate = function() {
		location.href = "${contextPath}/reservation/choiceScheduleForm.do";
	};
</script>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
</style>

</head>
<body>
<h2>예매 결과</h2>
	<div><h3>${reservationInfo.movies_title}</h3>${reservationInfo.movies_age_restriction}
	</div>
	
		<table>
						
			<tr>
				<td>예매자</td>
				<td>${reservationInfo.member_name}</td>
			</tr>
			<tr>
				<td>상영시작시간</td>
				<td>${reservationInfo.schedule_begin_date}</td>
			</tr>
			<tr>
				<td>좌석</td>
				<td>${reservationInfo.reservation_seats}</td>
			</tr>
			<tr>
				<td>휴대폰번호</td>
				<td>${reservationInfo.reservation_hp1}-${reservationInfo.reservation_hp2}-${reservationInfo.reservation_hp3}</td>
			</tr>
			<tr>
				<td>인원</td>
				<td>성인${reservationInfo.reservation_adult}명/청소년${reservationInfo.reservation_teenager}명</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${reservationInfo.reservation_price}원</td>
			</tr>


		</table>




</body>
</html>