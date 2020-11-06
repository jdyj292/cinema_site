<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
<style>
.window {
	display: inline-block;
	width: 366px;
	height: 34px;
	border: 3px solid #FAFAFA;
	background: white;
}

.main_input {
	width: 348px;
	height: 21px;
	margin: 6px 0 0 9px;
	border: 0;
	line-height: 21px;
	font-weight: bold;
	font-size: 16px;
	outline: none;
}

.btn1 {
	width: 54px;
	height: 40px;
	margin: 0;
	border: 0;
	vertical-align: top;
	background: #9FACED;
	color: #434863;
	font-size: 16px;
	font-weight: bold;
	border-radius: 1px;
	cursor: pointer;
	transition: background-color 0.5s ease;
}

.btn1:hover {
	background: #F2B950;
}

#search {
	float: left;
}

.log { 
    margin: 1px 10px 0px 5px;
    padding: 7px;
	font-size: 18px;
	font-weight: 800;
	float: right;
	
}
</style>

<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var loopSearch = true;
	function keywordSearch() {
		if (loopSearch == false)
			return;
		var value = document.frmSearch.searchWord.value;
		$.ajax({
			type : "get",
			async : true, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/movies/keywordSearch.do",
			data : {
				keyword : value
			},
			success : function(data, textStatus) {
				var jsonInfo = JSON.parse(data);
				displayResult(jsonInfo);
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다." + data);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");

			}
		}); //end ajax	
	}

	function displayResult(jsonInfo) {
		var count = jsonInfo.keyword.length;
		if (count > 0) {
			var html = '';
			for ( var i in jsonInfo.keyword) {
				html += "<a href=\"javascript:select('" + jsonInfo.keyword[i]
						+ "')\">" + jsonInfo.keyword[i] + "</a><br/>";
			}
			var listView = document.getElementById("suggestList");
			listView.innerHTML = html;
			show('suggest');
		} else {
			hide('suggest');
		}
	}

	function select(selectedKeyword) {
		document.frmSearch.searchWord.value = selectedKeyword;
		loopSearch = false;
		hide('suggest');
	}

	function show(elementId) {
		var element = document.getElementById(elementId);
		if (element) {
			element.style.display = 'block';
		}
	}

	function hide(elementId) {
		var element = document.getElementById(elementId);
		if (element) {
			element.style.display = 'none';
		}
	}

	function login() {
		sessionStorage.removeItem("action");
		location.href = "${contextPath}/member/loginForm.do"
	}
</script>

</head>
<body>



	<div id="search">
		<form name="frmSearch" action="${contextPath}/movies/searchMovies.do">
			<span class='window'> <input name="searchWord"
				class="main_input" type="text" onKeyUp="keywordSearch()">
			</span> <input type="submit" name="search" class="btn1" value="검색">
		</form>


	</div>


	<div class="log no-underline_white">
		<c:choose>
			<c:when test="${isLogOn == true  && memberInfo!= null}">
			
				<a href="${contextPath}/mypage/listMyReservationHistory.do">마이페이지</a>
				<span>|</span>               
				<a href="${contextPath}/member/logout.do">로그아웃</a>              

			</c:when>
			<c:otherwise>

				<a href="javascript:login()">로그인</a>

			</c:otherwise>
		</c:choose>
	</div>
	<div style="clear: both"></div>



</body>
</html>