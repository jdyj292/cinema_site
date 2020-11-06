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
		location.href = "${contextPath}/main/main.do";
	};

	window.onload = function() {
		var size = "${scheduleList.size()}";
		var age_restriction;
		var s_age_restriction;
		for (i = 0; i < size; i++) {
			age_restriction = $('input[name=\'movies_id\']').eq(i).attr(
					'value4');
			s_age_restriction = age_restriction.substr(0, 2);
			$('.age_restriction').eq(i).text(s_age_restriction);

		}

	}

	var i_movies_title;
	var i_movies_id;
	var i_schedule_id;
	var i_schedule_begin_day;
	var i_schedule_begin_time;
	var i_movies_age_restriction;
	var i_schedule_begin_date;
	var i_schedule_end_date;

	function movies_id_input() {

		i_movies_id = $('input[name=\'movies_id\']:checked').attr('value1');
		i_movies_title = $('input[name=\'movies_id\']:checked').attr('value2');
		i_movies_filename = $('input[name=\'movies_id\']:checked').attr(
				'value3');
		i_movies_age_restriction = $('input[name=\'movies_id\']:checked').attr(
				'value4');

		$("#timelist ").empty();
		$("#daylist ").empty();
		$("#movies_info").empty();
		$("#movies_info")
				.append(
						"<img width='180'  src='${contextPath}/thumbnails.do?movies_id="
								+ i_movies_id + "&fileName="
								+ i_movies_filename + "'>");
		$submit_button = $('#submit_button').attr('disabled', true);

		$
				.ajax({
					type : "post",
					async : false, //false인 경우 동기식으로 처리한다.
					url : "${contextPath}/reservation/searchScheduleDay.do",

					data : {
						movies_id : i_movies_id

					},

					success : function(data, textStatus) {

						var p_year = null;
						var p_month = null;
						for (i = 0; i < data.length; i++) {
							var date = data[i].split('-');
							var year = date[0];
							var month = date[1];
							var day = date[2];
							if (year != p_year || month != p_month) {
								$("#daylist").append(
										"<span class='date'>" + year + "<br>"
												+ month + "</span>" + "<br>");
							}
							$("#daylist")
									.append(
											"<input  type='radio' name='schedule_day' onclick='schedule_time_input()' value="
													+ data[i]
													+ ">"
													+ day
													+ "<br>");
							p_year = year;
							p_month = month;
						}

					},
					error : function(request, status, error) {

						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);

					},

					complete : function(data, textStatus) {
						//alert("작업을완료 했습니다");

					}
				}); //end ajax	

	}

	function schedule_time_input() {
		$("#timelist").empty();
		$submit_button = $('#submit_button').attr('disabled', true);

		i_schedule_begin_day = $('input[name=\'schedule_day\']:checked').val();

		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/reservation/searchScheduleTime.do",

			data : {
				movies_id : i_movies_id,
				schedule_day : i_schedule_begin_day
			},

			success : function(data, textStatus) {

				for (var i = 0; i < data.length; i++) {
					$("#timelist").append(
							"<input  type='radio' name='schedule_id' onclick='button_enabled()' value1="
									+ data[i].schedule_id + " value2="
									+ data[i].schedule_begin_date + " value3="
									+ data[i].schedule_end_date +">"
									+ data[i].schedule_begin_time);

				}

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

	function button_enabled() {
		i_schedule_id = $('input[name=\'schedule_id\']:checked').attr('value1');
		i_schedule_begin_date = $('input[name=\'schedule_id\']:checked').attr(
				'value2');
		i_schedule_end_date = $('input[name=\'schedule_id\']:checked').attr(
		'value3');
		$submit_button = $('#submit_button').attr('disabled', false);
        
	}

	function move_address_form() {
        
		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/reservation/checklogin.do",
			dataType : "text",
			data : {
				schedule_id : i_schedule_id,
				movies_title : i_movies_title,
				movies_id : i_movies_id,
				movies_filename : i_movies_filename,
				movies_age_restriction : i_movies_age_restriction,
				schedule_begin_date : i_schedule_begin_date,
				schedule_end_date : i_schedule_end_date
				
			},
			success : function(data, textStatus) {
				if (data == "needLogin") {
					alert("로그인이 필요합니다.");
					location.href = "${contextPath}/member/loginForm.do";
				} else if (data == "loginOn") {
					schedule_submit();
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
	
	function schedule_submit() {
		var formObj = document.createElement("form");

		document.body.appendChild(formObj);
		formObj.method = "post";
		formObj.action = "${contextPath}/reservation/updateAddressForm.do";
		formObj.submit();

	}
</script>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
.date {
	font-size: 20px;
	font-weight: bold;
	line-height: 40%;
}
.age_restriction{
    font-weight: 600;
}
</style>

</head>
<body>
	<form action="${contextPath}/reservation/updateAddressForm.do"
		method="post">
		<table
			style="width: 760px; table-layout: fixed; position: relative; left: -20px;">
			<tr>
				<td
					style="width: 270px; height: 25px; background-color:#E9EAF0; text-align: center;">영화</td>
				<td
					style="width: 50px; background-color:#E9EAF0; text-align: center;">날짜</td>
				<td
					style="width: 240px; background-color:#E9EAF0; text-align: center;">시간</td>
				<td
					style="width: 200px;  height: 320px;text-align: center; border: 1px solid black; background-color: black;"
					rowspan="2">
				<div 
						id="movies_info"></div> <br>
				<input style= "width: 180px; position: absolute; bottom:10px; right:15px;" type="button" id="submit_button"
					onclick="move_address_form()" value="다음단계" disabled></td>
			</tr>
			<tr valign="top">
				<td
					style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><c:forEach
						var="item" items="${scheduleList}">
						<input type="radio" name="movies_id" onclick="movies_id_input()"
							value1='${item.movies_id}' value2='${item.movies_title}'
							value3='${item.movies_fileName}'
							value4='${item.movies_age_restriction}'>
						<span class='age_restriction'></span>${item.movies_title}<br>
					</c:forEach></td>
				<td id="daylist" style="text-align: center;"></td>
				<td id="timelist"></td>

			</tr>
		</table>

	</form>
</body>
</html>