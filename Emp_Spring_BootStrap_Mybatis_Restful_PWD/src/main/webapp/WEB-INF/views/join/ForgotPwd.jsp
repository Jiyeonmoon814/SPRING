<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="row">
				<div class="col-md-6 col-md-offset-3" style="margin: 0 auto;"
					data-aos="fade-left">
					<h1>Log in</h1>
					<form action="" method="post">
						<div class="form-row">
							<div class="form-group col-md-6 col-md-offset-3 mb-6">
								<label>Enter your Email</label> <input type="text" name="emailforpwd"
									class="form-control" id="emailforpwd" />
							</div>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Confirm</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- End Hero -->
	<!-- Footer부분의 jsp -->
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<!-- Template 자체 Animation 및 Vendor 파일을 묶은 Foot.jsp -->
	<jsp:include page="/common/Foot.jsp"></jsp:include>
</body>
</html>

