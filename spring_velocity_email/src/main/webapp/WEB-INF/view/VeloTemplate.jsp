<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/register/check" var="urlAjax" />

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<link rel="stylesheet" href="./styles/main.css">
</head>
<body>
	
	<h1>Registration form</h1>
	
	<form id="registerForm" action="" method="post">
		<fieldset>
			<label>Email</label> <input type="text" name="email" />
		</fieldset>
		<fieldset>
			<label>Username</label> <input type="text" name="username" />
		</fieldset>
		<fieldset>
			<label>Password</label> <input type="password" name="password" />
		</fieldset>
		<fieldset>
			<label>Choose a type:</label> <select name="vm" id="vm">
				<option value="emailRegistration.vm">Basic</option>
				<option value="withStyle.vm">withStyle</option>
			</select>
		</fieldset>
		<input type="submit" value="Register" />
	</form>
</body>
</html>