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



  var cnt=0;
  function fn_addFile(){
	  if(cnt == 0){
		  $("#d_file").append("<br>"+"<input  type='file' name='main_image' id='f_main_image' />");	  
	  }else{
		  $("#d_file").append("<br>"+"<input  type='file' name='detail_image"+cnt+"' />");
	  }
  	
  	cnt++;
  }
  
  
  function fn_add_new_movies(obj){
	  		
		 fileName = $('#f_main_image').val();
		 if(fileName != null && fileName != undefined){
			 genre_input();
			 status_input();
			 obj.submit();
		 }else{
			 alert("메인 이미지는 반드시 첨부해야 합니다.");
			 return;
		 }
		 
	} 
  
  function genre_input(){
	  var values = document.getElementsByName("movies_genre");
	  var genre = '';
	  var count = 1;
	  for(var i=0; i<values.length;i++){
		  if(values[i].checked){
			  if(count == 1){
				  genre = values[i].value;
				  count = 2;
				  continue;
			  }
			  genre = genre+','+values[i].value;
		  }
	  }
	  $("input[type=checkbox][name=movies_genre]").val(genre);
  }
  
  function status_input(){
	  var movies_playdate =$("input[name=movies_playdate]").val();
	  var date = new Date(movies_playdate);
	  var today = new Date();
	  if(date>today){
		  $("input[name=movies_status]").val("개봉예정");
	  }
  }
  
  

</script>    
</head>

<BODY>
<form action="${contextPath}/admin/movies/addNewMovies.do" method="post"  enctype="multipart/form-data">
		<h1>새상품 등록창</h1>
<div class="tab_container">
	<!-- 내용 들어 가는 곳 -->
	
		
			
				<table >
		
			<tr >
				<td >영화이름</td>
				<td><input name="movies_title" type="text" size="40" /></td>
			</tr>
			
			<tr>
				<td >장르</td>
				<td id="a">
				<input type="checkbox" name="movies_genre" value="드라마"/>드라마
				<input type="checkbox" name="movies_genre" value="판타지"/>판타지
				<input type="checkbox" name="movies_genre" value="로맨스"/>로맨스
				<input type="checkbox" name="movies_genre" value="공포"/>공포 <br>
				<input type="checkbox" name="movies_genre" value="다큐멘터리"/>다큐멘터리
				<input type="checkbox" name="movies_genre" value="코미디"/>코미디
				<input type="checkbox" name="movies_genre" value="가족"/>가족
				<input type="checkbox" name="movies_genre" value="애니메이션"/>애니메이션
				<input type="checkbox" name="movies_genre" value="액션"/>액션
				<input type="checkbox" name="movies_genre" value="SF"/>SF
				</td>
			</tr>
			<tr>
				<td >국가</td>
				<td>
				<select name="movies_country">
    			<option value="미국">미국</option>
   				<option value="중국">중국</option>
    			<option value="일본">일본</option>
    			<option value="한국">한국</option>
    			<option value="영국">영국</option>
				</select>
				</td>
			</tr>
			<tr>
				<td >상영시간</td>
				<td><input name="movies_running_time" type="text" size="38" />분</td>
			</tr>
		
			
			<tr>
				<td >개봉일</td>
				<td><input  name="movies_playdate"  type="date" size="40" /></td>
			</tr>
			
			<tr>
				<td >나이제한</td>
				<td>
				<input type="radio" name="movies_age_restriction" value="전체 관람가" />전체 관람가
				<input type="radio" name="movies_age_restriction" value="12세 관람가"/> 12세 관람가
				<input type="radio" name="movies_age_restriction" value="15세 관람가"/> 15세 관람가
				<input type="radio" name="movies_age_restriction" value="청소년 관람불가"/>청소년 관람불가
			
				</td>
			</tr>
									
			<tr>
				<td >감독</td>
				<td><input name="movies_director" type="text" size="40" /></td>
			</tr>
			
			<tr>
				<td >출연진</td>
				<td><input name="movies_cast" type="text" size="40" /></td>
			</tr>
			
			<tr>
				<td >줄거리</td>
				<td><textarea  cols="50" rows="25"  name="movies_summary"></textarea></td>
			</tr>
			
			<tr>
			 <td>
               <input type="hidden" name="movies_status" value="상영종료">
			 </td>
			</tr>
				</table>	
		<table >
					<tr>
						<td align="right">이미지파일 첨부</td>
			            
			            <td  align="left"> <input type="button"  value="파일 추가" onClick="fn_addFile()"/></td>
			            <td>
				            <div id="d_file">
				            </div>
			            </td>
					</tr>
				</table>
			
			
			
		
	</div>
	

	 <table>
	 <tr>
			  <td align=center>
				
				    <input  type="button" value="상품 등록하기"  onClick="fn_add_new_movies(this.form)">
			  </td>
			</tr>
	 </table>

	
	 

</form>	 