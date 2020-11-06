<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="euc-kr"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<head>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>

<script type="text/javascript">
window.onload=function()
{
	schedule_begin_date_output();
	schedule_end_date_output();
}



function fn_modify_schedule(schedule_id, attribute){
	var frm_mod_schedule=document.frm_mod_schedule;
	var value="";
	if(attribute=='movies_id'){
		value=frm_mod_schedule.movies_id.value;
	}else if(attribute=='movies_title'){
		value=frm_mod_schedule.movies_title.value;
	}else if(attribute=='schedule_place'){
		value=frm_mod_schedule.schedule_place.value;
	}else if(attribute=='schedule_begin_date'){
		schedule_begin_date_input();
		value=frm_mod_schedule.schedule_begin_date.value;   
	}else if(attribute=='schedule_end_date'){
		schedule_end_date_input();
		value=frm_mod_schedule.schedule_end_date.value;
	}

	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/admin/schedule/modifyScheduleInfo.do",
		data : {
			schedule_id:schedule_id,
			attribute:attribute,
			value:value
		},
		
		success : function(data, textStatus) {
			if(data.trim()=='mod_success'){
				alert("상품 정보를 수정했습니다.");
			}else if(data.trim()=='failed'){
				alert("다시 시도해 주세요.");	
			}
			
		},
		error : function(data, textStatus) {
			alert("에러가 발생했습니다."+data);
		},
		complete : function(data, textStatus) {
			//alert("작업을완료 했습니다");
			
		}
	}); //end ajax	
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
  
function schedule_begin_date_output(){
	
	var strArray ="${scheduleVO.schedule_begin_date }".split(" ");
	var date = strArray[0];
	var time = strArray[1].substring(0,8);
	$("input[type=date][name=schedule_begin_day]").val(date);
	$("input[type=time][name=schedule_begin_time]").val(time);
}

function schedule_end_date_output(){
	
	var strArray ="${scheduleVO.schedule_end_date }".split(" ");
	var date = strArray[0];
	var time = strArray[1].substring(0,8);
	$("input[type=date][name=schedule_end_day]").val(date);
	$("input[type=time][name=schedule_end_time]").val(time);
}
  

  

</script>

</HEAD>
<BODY>
<form  name="frm_mod_schedule"  method=post >


	
	
			
				<table >
			
			<tr >
				<td >상품이름</td>
				<td><input name="movies_id" type="text" size="40"  value="${scheduleVO.movies_id }"/></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_schedule('${scheduleVO.schedule_id }','movies_id')"/>
				</td>
			</tr>
			
			
			<tr >
				<td >상품이름</td>
				<td><input name="movies_title" type="text" size="40"  value="${scheduleVO.movies_title }"/></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_schedule('${scheduleVO.schedule_id }','movies_title')"/>
				</td>
			</tr>
			
			<tr>
				<td >상영관</td>
				<td><input name="schedule_place" type="text" size="40" value="${scheduleVO.schedule_place }" /></td>
								<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_schedule('${scheduleVO.schedule_id }','schedule_place')"/>
				</td>
				
			</tr>
			<tr>
				<td >상영시작시간</td>
				<td><input name="schedule_begin_day" type="date"  /></td>
				<td><input name="schedule_begin_time" type="time"  /></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_schedule('${scheduleVO.schedule_id }','schedule_begin_date')"/>
				</td>
				
			</tr>
		
			
			<tr>
				<td >상영종료시간</td>
				<td><input name="schedule_end_day" type="date"  /></td>
				<td><input name="schedule_end_time" type="time"  /></td>
				<td>
				 <input  type="button" value="수정반영"  onClick="fn_modify_schedule('${scheduleVO.schedule_id }','schedule_end_date')"/>
				</td>
				
			</tr>
			
			<tr >
				<td><input name="schedule_begin_date" type="hidden" size="40" /></td>
				<td><input name="schedule_end_date" type="hidden" size="40" /></td>
			</tr>
			
			

			
			
			</table>
			
	
			
			
			
	

	
					
</form>	