<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/resources/styles/main.css" var="urlCssMain"/>    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
		<link href="${urlCssMain}" rel="stylesheet"/>
	</head> 
	<body>
		<h2>${message}</h2>
		<br><br>
		<li><a href="Main.do">Go Back to Main</a></li>
	</body>
</html>
