<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>
</head>
<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>

	<c:set var="emplist" value="${requestScope.map.emplist}" />
	<c:set var="pagesize" value="${requestScope.map.pagesize}" />
	<c:set var="pagecount" value="${requestScope.map.pagecount}" />
	<c:set var="currpage" value="${requestScope.map.currpage}" />

	<section id="hero">
		<div class="container">

			<div class="form-group">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						<label for="" style="margin-bottom: 0">출력 개수 설정</label>
						<div class="form-group d-flex align-items-center">
							<div class="col-sm-2" style="padding-left: 0">

								<form name="list">
									<!-- form = list -->

									<!-- select -->
									<select name="ps" class="form-control" onchange="submit()">

										<c:forEach var="count" begin="5" end="20" step="5">
											<c:choose>
												<c:when test="${pagesize == count}">
													<option value="${count}" selected>${count}</option>
												</c:when>
												<c:otherwise>
													<option value="${count}">${count}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>

									</select>

								</form>

							</div>

						</div>

					</div>
					<div class="col-sm-12 col-md-6">
						<div class="d-flex justify-content-end">
						<form action="search.member" method="post">
							<label for=""> 
								<input id="search" name="ename"
									class="form-control" placeholder="이름 검색">
							</label>&nbsp;
							<input type="submit" class="btn btn-success" value="검색">
						</form>
						</div>
					</div>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading"><h2>사원정보</h2></header>
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>이미지</th>
										<th>사번</th>
										<th>성함</th>
										<th>직종</th>
										<th>사수(사번)</th>
										<th>입사일(Date)</th>
										<th>월급($)</th>
										<th>수당($)</th>
										<th>부서번호(DEPTNO)</th>
										<th>정보 수정</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="emp" items="${emplist}" varStatus="status">
										
										<tr>
										
											<c:choose>
												<c:when test="${emp.filename == null}">
													<td><img
											src="upload/null.jpg"
											style="width: 50px; height: 50px;"></td>
												</c:when>
												<c:otherwise>
													<td><img
											src="upload/${emp.filename}"
											style="width: 50px; height: 50px;"></td>
												</c:otherwise>
											</c:choose>
											
											<td>${emp.empno}</td>
											<td>${emp.ename}</td>
											<td>${emp.job}</td>
											<td>${emp.mgr}</td>
											<td>${emp.hiredate}</td>
											<td>${emp.sal}</td>
											<c:choose>
												<c:when test="${emp.comm == 0}">
													<td>없음</td>
												</c:when>
												<c:otherwise>
													<td>${emp.comm}</td>
												</c:otherwise>
											</c:choose>
											<td>${emp.deptno}</td>
											<td><a class="btn btn-success" href="EditEmp.member?empno=${emp.empno}">수정</a><a
												class="btn btn-danger" href="DeleteEmp.member?empno=${emp.empno}">삭제</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</section>
				</div>
			</div>

			<div class="d-flex justify-content-between mt-0">
				<ul class="pagination pagination-lg" style="margin: 0 auto">
					<c:if test="${currpage>1}">
						<!-- 이전 버튼 생성 조건 -->
						<li class="page-item"><a
							href="ShowEmpList.member?cp=${currpage-1}&ps=${pagesize}"
							class="page-link">Previous</a></li>
					</c:if>

					<!-- 페이지 번호 -->
					<c:forEach var="pnum" begin="1" end="${pagecount}" step="1">
						<c:choose>
							<c:when test="${currpage == pnum}">
								<li class="page-item"><a href="" class="page-link">${pnum}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a
									href="ShowEmpList.member?cp=${pnum}&ps=${pagesize}" class="page-link">${pnum}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${currpage < pagecount}">
						<li class="page-item"><a
							href="ShowEmpList.member?cp=${currpage+1}&ps=${pagesize}"
							class="page-link">Next</a></li>
					</c:if>
				</ul>
			</div>

			<!-- emplist 구분선 -->
			<hr>

			<!-- 비동기 검색 부분 시작 -->
			
			
		</div>
	</section>
	<!-- Footer부분의 jsp -->
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<!-- Template 자체 Animation 및 Vendor 파일을 묶은 Foot.jsp -->
	<jsp:include page="/common/Foot.jsp"></jsp:include>
</body>
</html>