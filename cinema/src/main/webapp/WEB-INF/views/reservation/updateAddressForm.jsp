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

    

	
	$(document).ready(function() {
		if('${memberInfo}'== null){
			location.href = "${contextPath}/reservation/choiceScheduleForm.do";
		}
		if('${resProcedure}' !='3단계'){
			location.href = "${contextPath}/reservation/choiceScheduleForm.do";
		}
		$("#select['name=hp1']").val("${hp1}").prop("selected", true);
		});
	

	function email_input() {
		var email = $("select[name='email3']").val();
		$("input[name='reservation_email2']").val(email);
	}
	
	function address_input(obj){
		if($("input[name=reservation_hp2]").val()==""||$("input[name=reservation_hp3]").val()==""){
			alert("휴대폰 번호를 입력해주세요 ");
		}
		else{
			obj.submit();
		}
		
	}
</script>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
</style>

</head>
<body>
	<form action="${contextPath}/reservation/selectSeatsForm.do"
		method="post">
		<table
			style="width: 760px; table-layout: fixed; position: relative; left: -20px;">
			<tr>
				<td
					style="width: 560px; border: 1px solid black; text-align: center;">
					<table style=" text-align: left;">
						<tr>
							<td colspan='2'>예매 완료시 예매번호를 전송해 드립니다.<br> 상영시간 변경/취소 등
								긴급한 경우 휴대폰으로 연락드립니다.
							</td>
						</tr>
						<tr>
							<td >예매자</td>
							<td>${memberInfo.member_name}</td>
						</tr>
						<tr class="dot_line">
							<td class="fixed_join">휴대폰번호</td>
							<td><select name="reservation_hp1">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
							</select> - <input size="10px" type="text" name="reservation_hp2"
								value="${memberInfo.hp2}"> - <input size="10px" type="text"
								name="reservation_hp3" value="${memberInfo.hp3}"><br> <br>
							</td>
						</tr>
						<tr class="dot_line">
							<td class="fixed_join">이메일</td>
							<td><input size="10px" type="text" name="reservation_email1"
								value="${memberInfo.email1}" /> @ <input size="10px" type="text"
								name="reservation_email2" value="${memberInfo.email2}" /> <select
								name="email3" onChange="email_input()" title="직접입력">
									<option value="">직접입력</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="naver.com">naver.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="paran.com">paran.com</option>
									<option value="nate.com">nate.com</option>
									<option value="google.com">google.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="empal.com">empal.com</option>
									<option value="korea.com">korea.com</option>
									<option value="freechal.com">freechal.com</option>
							</select></td>
						</tr>
					</table>
				</td>
				<td
					style="width: 200px; text-align: center; border: 1px solid black; background-color: black;"
					rowspan="2">
					<div style="margin: 10px 0px 30px 0px; color: white;">
						<img width='180'
							src='${contextPath}/thumbnails.do?movies_id=${reservationInfo.movies_id}&fileName=${reservationInfo.movies_filename}'>

						${reservationInfo.movies_title} <br>
						${reservationInfo.movies_age_restriction} <br>
						${reservationInfo.schedule_begin_date}
					</div> <input
					style="width: 180px; position: absolute; bottom: 10px; right: 15px;" id="submit_button"
					type="button" onclick="address_input(this.form)" value="다음단계"><br>
				</td>
			</tr>

		</table>

	</form>
</body>
</html>