<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="euc-kr"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="movies"  value="${moviesMap.movies}"  />
<c:set var="imageFileList"  value="${moviesMap.imageFileList}"  />

<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.6.2.min.js"></script>
<c:choose>
<c:when test='${not empty movies.movies_status}'>
<script>

$(document).ready(function() {

	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});


</script>
</c:when>
</c:choose>
<script type="text/javascript">
function fn_modify_movies(movies_id, attribute){
	var frm_mod_movies=document.frm_mod_movies;
	var value="";
	if(attribute=='movies_title'){
		value=frm_mod_movies.movies_title.value;
	}else if(attribute=='movies_status'){
		value=frm_mod_movies.movies_status.value;
	}else if(attribute=='movies_genre'){
		value=frm_mod_movies.movies_genre.value;   
	}else if(attribute=='movies_country'){
		value=frm_mod_movies.movies_country.value;
	}else if(attribute=='movies_running_time'){
		value=frm_mod_movies.movies_running_time.value;
	}else if(attribute=='movies_playdate'){
		value=frm_mod_movies.movies_playdate.value;
	}else if(attribute=='movies_age_restriction'){
		value=frm_mod_movies.movies_age_restriction.value;
	}else if(attribute=='movies_total_attendance'){
		value=frm_mod_movies.movies_total_attendance.value;
	}else if(attribute=='movies_director'){
		value=frm_mod_movies.movies_director.value;
	}else if(attribute=='movies_cast'){
		value=frm_mod_movies.movies_cast.value;
	}else if(attribute=='movies_summary'){
		value=frm_mod_movies.movies_summary.value;
	}

	$.ajax({
		type : "post",
		async : false, //false�� ��� ��������� ó���Ѵ�.
		url : "${contextPath}/admin/movies/modifyMoviesInfo.do",
		data : {
			movies_id:movies_id,
			attribute:attribute,
			value:value
		},
		
		success : function(data, textStatus) {
			if(data.trim()=='mod_success'){
				alert("��ǰ ������ �����߽��ϴ�.");
			}else if(data.trim()=='failed'){
				alert("�ٽ� �õ��� �ּ���.");	
			}
			
		},
		error : function(data, textStatus) {
			alert("������ �߻��߽��ϴ�."+data);
		},
		complete : function(data, textStatus) {
			//alert("�۾����Ϸ� �߽��ϴ�");
			
		}
	}); //end ajax	
}



  function readURL(input,preview) {
	//  alert(preview);
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#'+preview).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
  }  

  var cnt =1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input  type='file' name='detail_image"+cnt+"' id='detail_image"+cnt+"'  onchange=readURL(this,'previewImage"+cnt+"') />");
	  $("#d_file").append("<img  id='previewImage"+cnt+"'   width=200 height=200  />");
	  $("#d_file").append("<input  type='button' value='�߰�'  onClick=addNewImageFile('detail_image"+cnt+"','${imageFileList[0].movies_id}','detail_image')  />");
	  cnt++;
  }
  
  function modifyImageFile(fileId,movies_id, image_id,fileType){
    // alert(fileId);
	  var form = $('#FILE_FORM')[0];
      var formData = new FormData(form);
      formData.append("fileName", $('#'+fileId)[0].files[0]);
      formData.append("movies_id", movies_id);
      formData.append("image_id", image_id);
      formData.append("fileType", fileType);
      
      $.ajax({
        url: '${contextPath}/admin/movies/modifyMoviesImageInfo.do',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
	      success: function(result){
	         alert("�̹����� �����߽��ϴ�!");
	       }
      });
  }
  
  function addNewImageFile(fileId,movies_id, fileType){
	   //  alert(fileId);
		  var form = $('#FILE_FORM')[0];
	      var formData = new FormData(form);
	      formData.append("uploadFile", $('#'+fileId)[0].files[0]);
	      formData.append("movies_id", movies_id);
	      formData.append("fileType", fileType);
	      
	      $.ajax({
	          url: '${contextPath}/admin/movies/addNewMoviesImage.do',
	                  processData: false,
	                  contentType: false,
	                  data: formData,
	                  type: 'post',
	                  success: function(result){
	                      alert("�̹����� �����߽��ϴ�!");
	                  }
	          });
	  }
  
  function deleteImageFile(movies_id,image_id,imageFileName,trId){
	var tr = document.getElementById(trId);

      	$.ajax({
    		type : "post",
    		async : true, //false�� ��� ��������� ó���Ѵ�.
    		url : "${contextPath}/admin/movies/removeMoviesImage.do",
    		data: {movies_id:movies_id,
     	         image_id:image_id,
     	         imageFileName:imageFileName},
    		success : function(data, textStatus) {
    			alert("�̹����� �����߽��ϴ�!!");
                tr.style.display = 'none';
    		},
    		error : function(data, textStatus) {
    			alert("������ �߻��߽��ϴ�."+textStatus);
    		},
    		complete : function(data, textStatus) {
    			//alert("�۾����Ϸ� �߽��ϴ�");
    			
    		}
    	}); //end ajax	
  }
</script>

</HEAD>
<BODY>
<form  name="frm_mod_movies"  method=post >
<DIV class="clear"></DIV>
	<!-- ���� ��� ���� �� -->

		
	
				<table >
			
			<tr >
				<td >��ǰ�̸�</td>
				<td><input name="movies_title" type="text" size="40"  value="${movies.movies_title }"/></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_title')"/>
				</td>
			</tr>
			
			<tr>
				<td >�帣</td>
				<td><input name="movies_genre" type="text" size="40" value="${movies.movies_genre }" /></td>
								<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_genre')"/>
				</td>
				
			</tr>
			<tr>
				<td >����</td>
				<td><input name="movies_country" type="text" size="40" value="${movies.movies_country }" /></td>
			     <td>
				  <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_country')"/>
				</td>
				
			</tr>
			<tr>
				<td >�󿵽ð�</td>
				<td><input name="movies_running_time" type="text" size="40" value="${movies.movies_running_time }" /></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_running_time')"/>
				</td>
				
			</tr>
			
			<tr>
				<td >������</td>
				<td><input name="movies_playdate" type="date" value="${movies.movies_playdate }" /></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_playdate')"/>
				</td>
				
			</tr>
			
			
			<tr>
				<td >��������</td>
				<td><input name="movies_age_restriction" type="text" size="40" value="${movies.movies_age_restriction }" /></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_age_restriction')"/>
				</td>

			</tr>
			
			<tr>
				<td >����</td>
				<td><input name="movies_director" type="text" size="40" value="${movies.movies_director }"/></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_director')"/>
				</td>
			</tr>
			
			<tr>
				<td >�⿬��</td>
				<td><input name="movies_cast" type="text" size="40" value="${movies.movies_cast }"/></td>
				<td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_cast')"/>
				</td>
			</tr>
			
			<tr>
				<td >�ٰŸ�</td>
				<td><textarea  cols="50" rows="25"  name="movies_summary">${movies.movies_summary}</textarea></td>
			    <td>
				 <input  type="button" value="�����ݿ�"  onClick="fn_modify_movies('${movies.movies_id }','movies_summary')"/>
				</td>
			</tr>

		
					
			
			
			<tr>
			 <td colspan=3>
			   <br>
			 </td>
			</tr>
				</table>	
		
		
				<h4>��ǰ�̹���</h4>
				 <table>
					 <tr>
					<c:forEach var="item" items="${imageFileList }"  varStatus="itemNum">
			        <c:choose>
			            <c:when test="${item.fileType=='main_image' }">
			              <tr>
						    <td>���� �̹���</td>
						    <td>
							  <input type="file"  id="main_image"  name="main_image"  onchange="readURL(this,'preview${itemNum.count}');" />
						      <%-- <input type="text" id="image_id${itemNum.count }"  value="${item.fileName }" disabled  /> --%>
							  <input type="hidden"  name="image_id" value="${item.image_id}"  />
							<br>
						</td>
						<td>
						  <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?movies_id=${item.movies_id}&fileName=${item.fileName}" />
						</td>
						<td>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>
						 <input  type="button" value="����"  onClick="modifyImageFile('main_image','${item.movies_id}','${item.image_id}','${item.fileType}')"/>
						</td> 
					</tr>
					<tr>
					 <td>
					   <br>
					 </td>
					</tr>
			         </c:when>
			         <c:otherwise>
			           <tr  id="${itemNum.count-1}">
						<td>�� �̹���${itemNum.count-1 }</td>
						<td>
							<input type="file" name="detail_image"  id="detail_image"   onchange="readURL(this,'preview${itemNum.count}');" />
							<%-- <input type="text" id="image_id${itemNum.count }"  value="${item.fileName }" disabled  /> --%>
							<input type="hidden"  name="image_id" value="${item.image_id }"  />
							<br>
						</td>
						<td>
						  <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?movies_id=${item.movies_id}&fileName=${item.fileName}">
						</td>
						<td>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						 <td>
						 <input  type="button" value="����"  onClick="modifyImageFile('detail_image','${item.movies_id}','${item.image_id}','${item.fileType}')"/>
						  <input  type="button" value="����"  onClick="deleteImageFile('${item.movies_id}','${item.image_id}','${item.fileName}','${itemNum.count-1}')"/>
						</td> 
					</tr>
					<tr>
					 <td>
					   <br>
					 </td>
					</tr> 
			         </c:otherwise>
			       </c:choose>		
			    </c:forEach>
			    <tr align="center">
			      <td colspan="3">
				      <div id="d_file">
						  <%-- <img  id="preview${itemNum.count }"   width=200 height=200 src="${contextPath}/download.do?movies_id=${item.movies_id}&fileName=${item.fileName}" /> --%>
				      </div>
			       </td>
			    </tr>
		   <tr>
		     <td align=center colspan=2>
		     
		     <input   type="button" value="�̹��������߰��ϱ�"  onClick="fn_addFile()"  />
		   </td>
		</tr> 
	</table>
	</form>
	<DIV class="clear"></DIV>
					
