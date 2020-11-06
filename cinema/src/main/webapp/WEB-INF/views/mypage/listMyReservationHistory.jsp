<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta   charset="utf-8">
<c:if test="${message=='cancel_reservation'}">
	<script>
	window.onload=function()
	{
	  init();
	}
	
	function init(){
		alert("주문을 취소했습니다.");
	}
	</script>
</c:if>
<script>
$(function(){
	  
	$("select[name='date_condition']").val("${dateCondition}").prop("selected", true);
	$("input[name='search_word']").val("${searchWord}");
	
	  					  
});

function search_reservation_list(){
	var formObj=document.createElement("form");
	
	var i_fixedSearch_period = document.createElement("input");
	var i_date_condition = document.createElement("input");
	var i_search_word = document.createElement("input");
	
	
	i_fixedSearch_period.name="fixedSearchPeriod";  
    i_date_condition.name="dateCondition";
    i_search_word.name="searchWord";
    
    i_fixedSearch_period.value=calcPeriod($("select[name='date_condition']").val());
    i_date_condition.value=$("select[name='date_condition']").val();
    i_search_word.value=$("input[name='search_word']").val();
    
    formObj.appendChild(i_fixedSearch_period);
    formObj.appendChild(i_date_condition);
    formObj.appendChild(i_search_word);

    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/mypage/listMyReservationHistory.do";
    formObj.submit();
    
}


function  calcPeriod(search_period){
	var dt = new Date();
	var beginYear,endYear;
	var beginMonth,endMonth;
	var beginDay,endDay;
	var beginDate,endDate;
	
	endYear = dt.getFullYear();
	endMonth = dt.getMonth()+1;
	endDay = dt.getDate();
	if(search_period=='all'){		
		beginYear = 1900
		beginMonth = 00
		beginDay = 00
	}else if(search_period=='today'){
		beginYear=endYear;
		beginMonth=endMonth;
		beginDay=endDay;
	}else if(search_period=='one_week'){
		dt.setDate(endDay-7);
		beginYear=dt.getFullYear();
		beginMonth=dt.getMonth()+1;
		beginDay=dt.getDate();
		
	}else if(search_period=='one_month'){
		dt.setMonth(endMonth-1);
		beginYear = dt.getFullYear();
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();

	}else if(search_period=='one_year'){
		dt.setYear(endYear-1);
		beginYear = dt.getFullYear();
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}
	
	if(beginMonth <10){
		beginMonth='0'+beginMonth;}
		if(beginDay<10){
			beginDay='0'+beginDay;
		}
	
	if(endMonth <10){
		endMonth='0'+endMonth;}
		if(endDay<10){
			endDay='0'+endDay;
		}
	
	endDate=endYear+'-'+endMonth +'-'+endDay;
	beginDate=beginYear+'-'+beginMonth +'-'+beginDay;
	alert(beginDate+","+endDate);

	return beginDate+","+endDate;
}


</script>
</head>
<body>
	<H3>예매 내역 조회</H3>
	<form  method="post">	
		<table>
			<TBODY>
				<tr>
				  <td>
			
					<input  type="text" name="search_word" size="30"  />  
					<select name="date_condition"  >
						<option value="all" checked>상영일</option>
						<option value="today">지난 1일</option>
						<option value="one_week">지난 1주</option>
						<option value="one_month">지난 1달</option>
						<option value="one_year">지난 1년</option>
					</select>
					<input  type="button"  value="조회" onclick="search_reservation_list()" />
				  </td>
				</tr>
				<tr>
				  <td>
					조회한 기간:<input  type="text"  size="4" value="${beginYear}" disabled />년
							<input  type="text"  size="4" value="${beginMonth}"disabled />월	
							 <input  type="text"  size="4" value="${beginDay}"disabled/>일	
							 &nbsp; ~
							<input  type="text"  size="4" value="${endYear}" disabled/>년 
							<input  type="text"  size="4" value="${endMonth}"disabled/>월	
							 <input  type="text"  size="4" value="${endDay}"disabled/>일							 
				  </td>
				</tr>
			</TBODY>
		</table>
		<div class="clear">
	</div>
</form>	
<div class="clear"></div>
<table class="list_view" style="width:700px; table-layout: fixed;">
		<tbody align=center >
			<tr style="background:#E9EAF0" >
				<td class="fixed"style="width:100px; " >예매번호</td>
				<td style="width:400px;">영화</td>
				<td style="width:150px;">상영일</td>
				<td style="width:50px;">관</td>
			</tr>
   <c:choose>
     <c:when test="${empty myReservationtList }">			
			<tr>
		       <td colspan=8 class="fixed">
				  <strong>주문한 상품이 없습니다.</strong>
			   </td>
		     </tr>
	 </c:when>
	 <c:otherwise> 
     <c:forEach var="item" items="${myReservationtList }" varStatus="i">
      <TR>        
				<TD>
				  <a href="${contextPath}/mypage/myReservationDetail.do?reservation_id=${item.reservation_id }"><strong>${item.reservation_id}</strong></a>
				</TD>
				<TD style="text-overflow:ellipsis; overflow:hidden; white-space: nowrap; ">
				  <a href="${contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }"> <strong>${item.movies_title}</strong></a>
				</TD>
				<td>
				     <strong>${item.schedule_begin_date}</strong> 
				</td>
				<td>
				    <strong>${item.schedule_place}</strong> 
				</td>
				
			</TR>
        	
	</c:forEach>
	</c:otherwise>
  </c:choose>
           <tr>
             <td colspan=8 class="fixed">
                 <c:forEach   var="page" begin="${startPage}" end="${endPage}" step="1" >
		         <c:if test="${section >1 && page==1 }">
		          <a href="${contextPath}/mypage/listMyReservationHistory.do?section=${section-1}&pageNum=${(section-2)*10 +1 }&fixedSearchPeriod=${fixedSearchPeriod}&dateCondition=${dateCondition}&searchWord=${searchWord}">이전 &nbsp;</a>
		         </c:if>
		       	          
		          <c:choose>
				<c:when test="${page == pageNum}">
					<b>${(section-1)*10 +page }</b>
				</c:when>
				<c:when test="${p != pageNum}">
					<a href="${contextPath}/mypage/listMyReservationHistory.do?section=${section}&pageNum=${page}&fixedSearchPeriod=${fixedSearchPeriod}&dateCondition=${dateCondition}&searchWord=${searchWord}">${(section-1)*10 +page }</a>
				</c:when>
				  </c:choose>
				  
		         <c:if test="${page ==10 }">
		          <a href="${contextPath}/mypage/listMyReservationHistory.do?section=${section+1}&pageNum=${section*10+1}&fixedSearchPeriod=${fixedSearchPeriod}&dateCondition=${dateCondition}&searchWord=${searchWord}">&nbsp; 다음</a>
		         </c:if> 
	      		</c:forEach> 
     
		</TBODY>
		
	</TABLE>
     	
	<div class="clear"></div>
</body>
</html>