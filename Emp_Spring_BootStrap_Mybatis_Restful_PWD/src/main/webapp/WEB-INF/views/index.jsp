<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/Head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/common/Top.jsp"></jsp:include>
	<section id="hero">
		<div class="container">
			<div class="row">

				<div
					class="col-lg-6 pt-5 pt-lg-0 order-2 order-lg-1 d-flex flex-column justify-content-center"
					data-aos="fade-up">
					<div>

				
						<h1>Team2 사이트 방문을 환영합니다.</h1>
						<a class="btn-get-started scrollto" href="Joinform.member">EMP insert</a>
						<a class="btn-get-started scrollto" href="ShowEmpList.member">EmpList</a>
						<a class="btn-get-started scrollto" href="ShowEmpListAjax.member">EmpList(AJAX)</a><br><br>
						<a class="btn-get-started scrollto" href="signup.member">Member Sign up</a>
						<a class="btn-get-started scrollto" href="RestView.member">Member List(RESTFUL)</a>
					</div>
				</div>

				<div class="col-lg-6 order-1 order-lg-2 hero-img" data-aos="fade-left">
					<img src="assets/img/hero-img.png" class="img-fluid" style="z-index: 988819;" alt="">
				</div>

			</div>
		</div>
	</section>
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<jsp:include page="/common/Foot.jsp"></jsp:include>
</body>
</html>


