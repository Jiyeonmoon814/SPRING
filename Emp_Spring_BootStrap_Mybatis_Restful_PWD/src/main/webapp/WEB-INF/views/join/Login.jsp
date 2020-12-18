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
					<form action="${pageContext.request.contextPath}/login"
						method="post">
						<div class="form-row">
							<div class="form-group col-md-6 col-md-offset-3 mb-6">
								<label>ID</label> <input type="text" name="userid"
									class="form-control" id="name" />
							</div>
							<div class="form-group col-md-6 col-md-offset-3 mb-6">
								<label>Password</label> <input type="password" name="password"
									class="form-control" id="name" />
							</div>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Log in</button>
							<c:if test="${param.error != null }">
								<div>
									<b>로그인 실패</b>
									<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
										<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
									</c:if>
								</div>
							</c:if>
						</div>
						<a href="${pageContext.request.contextPath}/forgotPwd.member">Forgot your password?</a>
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

