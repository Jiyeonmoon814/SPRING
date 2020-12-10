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
		<li><a href="AttachmentsEmail.do">Send email with attachments</a></li>
		<li><a href="RegisterForm.do">Register Form</a></li>
		<li><a href="SummerNoteEmail.do">Summer Note</a></li>
		<li><a href="RegisterForm.do">Register Form</a></li>
		<li><a href="RegisterForm.do">Register Form</a></li>
	</body>
</html>
