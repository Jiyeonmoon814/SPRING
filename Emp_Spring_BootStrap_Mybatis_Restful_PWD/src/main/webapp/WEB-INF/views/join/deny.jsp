<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>
</head>
<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>
	<section id="hero">
		<div class="container">
			<!-- 회원가입 Form -->
			<div class="row">
				<div class="col-md-12 col-lg-12 ">
					<section class="panel">
						<header class="panel-heading mb-5 text-center">
							<h1>권한이 없습니다</h1>
							<h3><a href="index.member">HOME 화면으로 돌아가기</a></h3>
						</header>
					</section>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer부분의 jsp -->
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<!-- Template 자체 Animation 및 Vendor 파일을 묶은 Foot.jsp -->
	<jsp:include page="/common/Foot.jsp"></jsp:include>

</body>
</html>