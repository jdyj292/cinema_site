<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<head>
<script>
function fn_cancel_reservation(){
	var answer=confirm("주문을 취소하시겠습니까?");
	if(answer==true){

		var formObj=document.createElement("form");
		var i_reservation_id = document.createElement("input"); 
		var i_reservation_seats = document.createElement("input"); 
		var i_schedule_id = document.createElement("input"); 
	    	
		i_reservation_id.name="reservation_id";
		i_reservation_seats.name="reservation_seats";
		i_schedule_id.name="schedule_id";
		
		i_reservation_id.value="${reservationVO.reservation_id}";
		i_reservation_seats.value="${reservationVO.reservation_seats}";
		i_schedule_id.value="${reservationVO.schedule_id}";
		
	    formObj.appendChild(i_reservation_id);
	    formObj.appendChild(i_reservation_seats);
	    formObj.appendChild(i_schedule_id);
	    
	    document.body.appendChild(formObj); 
	    formObj.method="post";
	    formObj.action="${contextPath}/mypage/cancelMyReservation.do";
	    formObj.submit();	
	}
	
}

</script>
</head>
<body>

  <div>
  <table>
                 <tr >
					<td>영화</td>
					<td>"${reservationVO.movies_title}"</td>
				 </tr>
				 <tr >
					<td>예매자</td>
					<td>"${reservationVO.member_name}"</td>
				 </tr>
				 <tr >
					<td>좌석</td>
					<td>"${reservationVO.reservation_seats}"</td>
				 </tr>
				 <tr >
					<td>전화번호</td>
					<td>"${reservationVO.reservation_hp1}${reservationVO.reservation_hp2}${reservationVO.reservation_hp3}"</td>
				 </tr>
				 <tr >
					<td>성인</td>
					<td>"${reservationVO.reservation_adult}"</td>
				 </tr>
				 <tr >
					<td>청소년</td>
					<td>"${reservationVO.reservation_teenager}"</td>
				 </tr>
				 <tr >
					<td>가격</td>
					<td>"${reservationVO.reservation_price}"</td>
				 </tr>
				<c:choose>
			   <c:when test="${reservation_status == '상영시작전'}">
			       <input  type="button" onClick="fn_cancel_reservation()" value="예매취소"  />
			   </c:when>
			   <c:otherwise>
			      <input  type="button" onClick="fn_cancel_reservation()" value="예매취소" disabled />
			   </c:otherwise>
			  </c:choose>
				 
				
  </table>

  </div>
  







</body>
	
	
			
			
			