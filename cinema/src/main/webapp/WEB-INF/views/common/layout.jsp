<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#body {
	background: #827C7E;
}

#container {
	position: absolute;
	left: 50%;
	margin-left: -550px;
	width: 1000px;
	height: 2000px;
}

#header {
	width: 850px;
	clear: both;
	margin-top: 10px;
	margin-bottom: 20px;
	margin-left: 160px;
	border: 0px solid #bcbcbc;
}

#sidebar-left {
	width: 150px;
	height: 100%;
	background-color: #E9EAF0;
	border: 0px solid #bcbcbc;
	font-size: 15px;
	position: fixed;
	top: 0px;
	line-height: 30px;
}

#content {
	width: 750px;
	margin-bottom: 5px;
	margin-left: 160px;
	padding: 40px 50px;
	float: left;
	background-color: white;
	border: 0px solid #bcbcbc;
}

#footer {
	width: 850px;
	clear: both;
	margin-left: 160px;
	border: 0px solid #bcbcbc;
}

.side_menu * {
	padding: 0px 10px;
	text-decoration: none;
	color: #434863;
}

.no-underline_white a {
	text-decoration: none;
	color: #F2F2F2;
}

.no-underline_black a {
	text-decoration: none;
	color: black;
}

.no-underline_black a:hover {
	text-decoration: none;
	color: #EA426C;
}

.no-underline_pely {
	padding: 5px 10px;
	text-decoration: none;
	color: #F2C550;
	font-size: 30px;
}
</style>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body id="body">

	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="sidebar-left">
			<tiles:insertAttribute name="side" />
		</div>
		<div id="content">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>

</body>
</html>