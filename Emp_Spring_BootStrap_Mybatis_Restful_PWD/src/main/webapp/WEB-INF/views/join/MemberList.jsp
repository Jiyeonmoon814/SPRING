<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>
</head>
<se:authorize access=""></se:authorize>
<script type="text/javascript" src="assets/js/emplist.js"></script>

<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>

	<%-- <c:set var="memberlist" value="${requestScope.memberlist}" /> --%>

	<section id="hero">
		<div class="container">

			<div class="form-group">
				<div class="row">
					 <div class="col-sm-12 col-md-12">
						<div class="d-flex justify-content-end">
							<label for=""> 
								<input type="text" id="search" name="userid"
									class="form-control" placeholder="아이디 검색">
							</label>&nbsp;
						</div>
					</div> 

				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading">
							<!-- <h2><input type="button" class='btn btn-success' value="계정정보" id="restconBtn"></h2> -->
						</header>
						<div id="exp" class="table-responsive">
							<span id="menuView">

							</span>
						</div>

					</section>
				</div>
			</div>


			<!-- emplist 구분선 -->
			<hr>

		</div>
	</section>
	<!-- Footer부분의 jsp -->
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<!-- Template 자체 Animation 및 Vendor 파일을 묶은 Foot.jsp -->
	<jsp:include page="/common/Foot.jsp"></jsp:include>
</body>
</html>