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



window.onload=function()
{
	if('${memberInfo}'== null){
		location.href = "${contextPath}/reservation/choiceScheduleForm.do";
	}	
	if('${resProcedure}' !='5단계'){
		location.href = "${contextPath}/reservation/choiceScheduleForm.do";
	}
}



function fn_pay_phone(){
	
	
	var e_card=document.getElementById("tr_pay_card");
	var e_phone=document.getElementById("tr_pay_phone");
	e_card.style.visibility="hidden";
	e_phone.style.visibility="visible";
}

function fn_pay_card(){
	var e_card=document.getElementById("tr_pay_card");
	var e_phone=document.getElementById("tr_pay_phone");
	e_card.style.visibility="visible";
	e_phone.style.visibility="hidden";
}






</script>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
</style>

</head>
<body>
	<form action="${contextPath}/reservation/addNewReservation.do"
		method="post">
		<div>
			<table style="width:700px; border-bottom:3px solid gray;">
				<tr>
					<td style="font-weight:bold; font-size: 27px;">${reservationInfo.movies_title}
					</td>
				</tr>
				<tr>
					<td>성인${reservationInfo.reservation_adult}명&nbsp					
					청소년${reservationInfo.reservation_teenager}명
					</td>
				</tr>
				<tr>
					<td>${reservationInfo.reservation_price}원
					</td>
				</tr>

			</table>
		</div>
		<div class="detail_table">
			<table>
				<tbody>

					<tr>
						<td><input type="radio" id="pay_method" name="pay_method"
							value="신용카드" onClick="fn_pay_card()" checked>신용카드
							&nbsp;&nbsp;&nbsp; <input type="radio" id="pay_method"
							name="pay_method" value="휴대폰결제" onClick="fn_pay_phone()">휴대폰
							결제 &nbsp;&nbsp;&nbsp;</td>
					</tr>

					<tr id="tr_pay_card">
						<td><strong>카드 선택<strong>:&nbsp;&nbsp;&nbsp;
									<select id="card_com_name" name="card_com_name">
										<option value="삼성" selected>삼성</option>
										<option value="하나SK">하나SK</option>
										<option value="현대">현대</option>
										<option value="KB">KB</option>
										<option value="신한">신한</option>
										<option value="롯데">롯데</option>
										<option value="BC">BC</option>
										<option value="시티">시티</option>
										<option value="NH농협">NH농협</option>
								</select> <br> <Br> <strong>할부 기간:<strong>
											&nbsp;&nbsp;&nbsp; <select id="card_pay_month"
											name="card_pay_month">
												<option value="일시불" selected>일시불</option>
												<option value="2개월">2개월</option>
												<option value="3개월">3개월</option>
												<option value="4개월">4개월</option>
												<option value="5개월">5개월</option>
												<option value="6개월">6개월</option>
										</select></td>
					</tr>
					<tr id="tr_pay_phone" style="visibility: hidden">
						<td><strong>휴대폰 번호 입력: <strong> <input
									type="text" size="5" value="" id="pay_order_tel1"
									name="pay_order_tel1" /> <input type="text" size="5" value=""
									id="pay_order_tel2" name="pay_order_tel2" /> <input
									type="text" size="5" value="" id="pay_order_tel3"
									name="pay_order_tel3" /></td>
					</tr>
				</tbody>
			</table>
		</div>

		<input style="width:60px;" type="submit" id="submit_button" value="결제">
	</form>
</body>
</html>