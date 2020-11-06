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

	window.onload = function() {
		if ('${memberInfo}' == null) {
			location.href = "${contextPath}/reservation/choiceScheduleForm.do";
		}
		if('${resProcedure}' !='4단계'){
			location.href = "${contextPath}/reservation/choiceScheduleForm.do";
		}
		seats_output();
		reservation_seats_input();
		adult_seating("adult_seats0");
		teenager_seating("teenager_seats0");
	}
	var seats = new Array(); //선택한 좌석 문자열 ex: a1,b2...
	var reservation_seats = new Array(); //에약된 좌석 문자열 ex: a1,b2...
	var adult = 0; //성인 총몇명
	var teenager = 0; //청소년 총몇명
	var schedule_id = "${reservationInfo.schedule_id}"

	function seats_output() {//좌석 생성
		var items = [ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' ];

		$("#seatslist").append("<table >");
		items.forEach(function(e) {
			$("#seatslist").append("<tr>");
			$("#seatslist").append("<td>" + e + "</td>");
			for (i = 1; i < 12; i++) {
				if (i == 4 || i == 9) {
					$("#seatslist").append("<td>&nbsp;&nbsp;&nbsp;<td>");
				}
				$("#seatslist").append(
						"<td><input  type='button' class='seats_button'  name="
								+ e + i
								+ " onclick='select_seats(this.name);' value="
								+ e + i + "></td>");
			}
			$("#seatslist").append("</tr>");
		});
		$("#seatslist").append("</table >");

	}

	function reservation_seats_input() { //예약된 좌석 가져오기

		$.ajax({
			type : "post",
			async : false,
			dataType : "json",
			traditional : "true",
			url : "${contextPath}/reservation/selectSeatsList.do",

			data : {
				schedule_id : schedule_id
			},

			success : function(data, textStatus) {

				reservation_seats = data;
			},
			error : function(request, status, error) {

				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);

			},

			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				

			}
		}); //end ajax	

	}

	function select_seats(name) {//좌석 선택
		var color = $("input[name=" + name + "]").css('background-color');//선택한 버튼의 색

		if (color == 'rgb(128, 128, 128)') { //색깔이 맞으면 seats에 추가
			$("input[name=" + name + "]").css('background', 'red');
			seats.push($("input[name=" + name + "]").val());
		} else {
			$("input[name=" + name + "]").css('background', 'gray'); //다르면 삭제
			seats.splice(seats.indexOf($("input[name=" + name + "]").val()), 1);
		}
		seats_limit();
	}

	function adult_seating(name) {//성인 인원수 선택
		$(".seats_button").css('background', 'gray'); //좌석 초기화
		seats.length = 0;

		$(".adult input").css('background', 'gray');
		$("input[name=" + name + "]").css('background', 'red');
		adult = $("input[name=" + name + "]").val();
		seats_limit(); //인원수를 넣은 다음에 if
	}

	function teenager_seating(name) {//청소년 인원수 선택
		$(".seats_button").css('background', 'gray'); //좌석 초기화
		seats.length = 0;

		$(".teenager input").css('background', 'gray');
		$("input[name=" + name + "]").css('background', 'red');
		teenager = $("input[name=" + name + "]").val();
		seats_limit();
	}

	function submit_seats() {//좌석 예약
		seats.sort();

		//alert(adult+" "+teenager+" "+seats+" "+schedule_id);

		$
				.ajax({
					type : "post",
					async : false,
					dataType : "json",
					traditional : "true",
					url : "${contextPath}/reservation/addNewSeats.do",
					data : {
						seats_id : seats,
						schedule_id : schedule_id
					},

					success : function(data, textStatus) {
						
						var formObj = document.createElement("form");

						var i_reservation_seats = document
								.createElement("input");
						var i_reservation_adult = document
								.createElement("input");
						var i_reservation_teenager = document
								.createElement("input");

						i_reservation_seats.name = "reservation_seats";
						i_reservation_adult.name = "reservation_adult";
						i_reservation_teenager.name = "reservation_teenager";

						i_reservation_seats.value = seats;
						i_reservation_adult.value = adult;
						i_reservation_teenager.value = teenager;

						formObj.appendChild(i_reservation_seats);
						formObj.appendChild(i_reservation_adult);
						formObj.appendChild(i_reservation_teenager);

						document.body.appendChild(formObj);
						formObj.method = "post";
						formObj.action = "${contextPath}/reservation/reservationForm.do";
						formObj.submit();

					},
					error : function(request, status, error) {
						seats_input(); //좌석 초기화
						alert("좌석을 다시 확인 해주세요")
						//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

					},

					complete : function(data, textStatus) {

					}
				}); //end ajax	
	}

	function seats_limit() {//인원수에 맞춰서 좌석을 비활성화
		if (Number(adult) + Number(teenager) == seats.length) { //인원수가 선택한 좌석수와 같을떄 
			$(".seats_button").attr('disabled', true);//모든 좌석 비활성화
			for (i = 0; i < seats.length; i++) {
				$("input[name=" + seats[i] + "]").attr('disabled', false); //시트에 저장된것만 활성화
			}
		} else if (Number(adult) + Number(teenager) > seats.length) { //인원수가 선택한 좌석수 보다 많을떄
			$(".seats_button").attr('disabled', false); //모든 좌석 활성화
			for (i = 0; i < reservation_seats.length; i++) {
				$("input[name=" + reservation_seats[i] + "]").attr('disabled',
						true);
			}
		}
	}

	function refresh_seats() {//새로고침
		$(".seats_button").css('background', 'gray'); //좌석 초기화
		seats.length = 0;

		reservation_seats_input();
		adult_seating("adult_seats0");
		teenager_seating("teenager_seats0");
	}
</script>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
.button {
	background: gray;	
}
.seats_button{
    width: 35px;
}
</style>

</head>
<body>
	<form action="${contextPath}/reservation/reservationForm.do"
		method="post">
		<table
			style="width: 760px; table-layout: fixed; position: relative; left: -20px;">
			<tr>
				<td
					style="width: 560px; border: 1px solid black; text-align: center;">
					<table style="text-align: center;">
						<tr class='adult'>
							<td>일반</td>
							<td><input type='button' class='button' name="adult_seats0"
								onclick='adult_seating(this.name);' value="0"></td>
							<td><input type='button' class='button' name="adult_seats1"
								onclick='adult_seating(this.name);' value="1"></td>
							<td><input type='button' class='button' name="adult_seats2"
								onclick='adult_seating(this.name);' value="2"></td>
							<td><input type='button' class='button' name="adult_seats3"
								onclick='adult_seating(this.name);' value="3"></td>
							<td><input type='button' class='button' name="adult_seats4"
								onclick='adult_seating(this.name);' value="4"></td>
							<td><input type='button' class='button' name="adult_seats5"
								onclick='adult_seating(this.name);' value="5"></td>
							<td><input type='button' onclick='refresh_seats();'
								value='새로고침'></td>
						</tr>
						<tr class='teenager'>
							<td>청소년</td>
							<td><input type='button' class='button'
								name="teenager_seats0" onclick='teenager_seating(this.name);'
								value="0"></td>
							<td><input type='button' class='button'
								name="teenager_seats1" onclick='teenager_seating(this.name);'
								value="1"></td>
							<td><input type='button' class='button'
								name="teenager_seats2" onclick='teenager_seating(this.name);'
								value="2"></td>
							<td><input type='button' class='button'
								name="teenager_seats3" onclick='teenager_seating(this.name);'
								value="3"></td>
							<td><input type='button' class='button'
								name="teenager_seats4" onclick='teenager_seating(this.name);'
								value="4"></td>
							<td><input type='button' class='button'
								name="teenager_seats5" onclick='teenager_seating(this.name);'
								value="5"></td>
						</tr>
					</table>
					<br>
					<div style="position:relative; left:50px; width: 400px; text-align: center; color: white; background-color: black;">screen</div>
					<div id='seatslist'></div>

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
					style="width: 180px; position: absolute; bottom: 10px; right: 15px;"
					id="submit_button" type="button" onclick='submit_seats(this.form);'
					value="다음단계"><br>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>