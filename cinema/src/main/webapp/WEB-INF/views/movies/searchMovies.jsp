<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
%> 
<head>
 <title>검색 결과</title>
</head>
<body>

	
	<table id="list_view">
		<tbody>
		  <c:forEach var="item" items="${moviesList }"> 
			<tr>
					<td class="movies_image">
						<a href="${contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id}">
							   <img width="75" alt="" src="${contextPath}/thumbnails.do?movies_id=${item.movies_id}&fileName=${item.movies_fileName}">
						</a>
					</td>
					<td class="movies_description">
						<h2>
							<a href="${contextPath}/movies/moviesDetail.do?movies_id=${item.movies_id }">${item.movies_title }</a>
						</h2>
					
					</td>
					
					<td><input type="checkbox" value=""></td>
					
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="clear"></div>
	<div id="page_wrap">
		<ul id="page_control">
			<li><a class="no_border" href="#">Prev</a></li>
			<c:set var="page_num" value="0" />
			<c:forEach var="count" begin="1" end="10" step="1">
				<c:choose>
					<c:when test="${count==1 }">
						<li><a class="page_contrl_active" href="#">${count+page_num*10 }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#">${count+page_num*10 }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a class="no_border" href="#">Next</a></li>
		</ul>
	</div>