<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />	
<!DOCTYPE html>

<meta charset="utf-8">
<head>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>

<script type="text/javascript">



  
  
  function fn_add_new_schedule(obj){
	  		
		
	  		 schedule_begin_date_input();
	  		 schedule_end_date_input();
			 obj.submit();

		 
	} 
  
  function schedule_begin_date_input(){
	  var date = document.getElementsByName("schedule_begin_day")[0].value;
	  var time = document.getElementsByName("schedule_begin_time")[0].value;
	  date = date+" "+time+":00";
	  $("input[type=hidden][name=schedule_begin_date]").val(date);
	  
	  
  }
  function schedule_end_date_input(){
	  var date = document.getElementsByName("schedule_end_day")[0].value;
	  var time = document.getElementsByName("schedule_end_time")[0].value;
	  date = date+" "+time+":00";
	  $("input[type=hidden][name=schedule_end_date]").val(date);
	 
	  
  }
  

</script>    

  
</head>

<BODY>
<form action="${contextPath}/admin/schedule/addNewSchedule.do" method="post"  enctype="multipart/form-data">
		<h1>새상영 등록창</h1>
<div class="tab_container">
	<!-- 내용 들어 가는 곳 -->
	
		
			
				<table >
		
			<tr >
				<td >영화번호</td>
				<td><input name="movies_id" type="text" size="40" /></td>
			</tr>
			
			<tr >
				<td >영화이름</td>
				<td><input name="movies_title" type="text" size="40" /></td>
			</tr>
			
			<tr>
				<td >상영관</td>
				<td><input name="schedule_place" type="text" size="40" /></td>
			</tr>
		
			
			<tr>
				<td >상영시간시간</td>
				<td><input  name="schedule_begin_day"  type="date" size="40" /></td>
				<td><input  name="schedule_begin_time"  type="time" size="40" /></td>
			</tr>
			
			<tr>
				<td >상영종료시간</td>
				<td><input  name="schedule_end_day"  type="date" size="40" /></td>
				<td><input  name="schedule_end_time"  type="time" size="40" /></td>
			</tr>
			
			<tr >
				
				<td><input name="schedule_begin_date" type="hidden" size="40" /></td>
				<td><input name="schedule_end_date" type="hidden" size="40" /></td>
			</tr>
			
			
			
		</table>
			
		
	</div>
	

	 <table>
	 <tr>
			  <td align=center>
				<!--   <input  type="submit" value="상품 등록하기"> --> 
				    <input  type="button" value="상품 등록하기"  onClick="fn_add_new_schedule(this.form)">
			  </td>
			</tr>
	 </table>
	 
	 
 
 
	 

</form>	 
	