<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>상세 페이지</title>
  <style>
  

     #star_grade a{
        text-decoration: none;
        color: black;
    }
    #star_grade a.on{
        color: red;
    }


</style>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script>
  $(function(){
        $('#star_grade a').click(function(){
       
            $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
            $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
            $('input[name=rating_value]').val( $('#star_grade a.on').length);
            return false;
        });
  });
  
  function submit_rating(obj){
	  if( $('input[name=rating_value]').val()==null||$('input[name=rating_value]').val()==0){
		  alert("평점을 입력해주세요.");
	  }
	  else if( $('input[name=rating_contend]').val()==null||$('input[name=rating_contend]').val()==""){
		  alert("감상평을 입력해주세요.");
	  }
	  else{
		  obj.submit();
	  }
  }
        
       
</script>

</head>
<body>
   <body>
	<h3>평점 등록</h3>
	<form action="${contextPath}/movies/addRating.do" method="post">	
	<div id="detail_table">
		<table>
			
				<tr class="dot_line">
					<td class="fixed_join"><input type="text" name="movies_title" value="${movies_title}" readonly></td>
					<td><input type="hidden" name="movies_id" value="${movies_id}"><td>
				</tr>
				<tr >
					
					<td><p id="star_grade">					       
        				<a href="#">★</a>
        				<a href="#">★</a>
        				<a href="#">★</a>
        				<a href="#">★</a>
        				<a href="#">★</a>
					</p></td>
				</tr>
				<tr>
					<td><input type="text" name="rating_value" readonly><td>
				</tr>
				<tr class="dot_line">
					<td ><input type="text" name="rating_contend" placeholder="감상평을 남겨주세요." style="width:300px;height:200px;font-size:30px;"></td>
				</tr>
				
		    <tr >
			   <td >
				<input type="button"  onclick="submit_rating(this.form)" value="입력"/>
				<input  type="button" onclick="history.back()" value="취소"/>
			   </td>
		    </tr>
	</table>
	</div>
</form>	
</body>
</html>