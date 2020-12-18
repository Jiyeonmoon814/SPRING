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
<script type="text/javascript">
$(function() {
	   $("#hiredate").datepicker({dateFormat : "y/mm/dd"});
	 }); 

</script>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>

	<section id="hero">
		<div class="container">
			<!-- Form validations -->
			<div class="row">
				<div class="col-md-12 col-lg-12 ">
					<section class="panel" >
					<c:set var="emp" value="${requestScope.emp}" />
						<header class="panel-heading mb-5 text-center"><h2><i>[ ${emp.ename} ]</i>사원 정보 수정</h2></header>
						<div class="panel-body">
							<div class="form">
							
								<!-- 회원 정보 수정하는 Form엘리먼트 속 데이터 -->
								<form action="UpdateEmp.member"
									class="form-validate form-horizontal" method="post">
									
									<div class="form-group ">
										<label class="control-label col-lg-2">사진 추가 예정</label>
										<div class="col-lg-12">
											<input class="form-control" name="empPics" value="추가 예정" type="text" readonly />
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-lg-2">사번</label>
										<div class="col-lg-12">
											<input class="form-control" name="empno" value="${emp.empno}" type="text" readonly />
										</div>
									</div>

									<div class="form-group ">
										<label class="control-label col-lg-2">성함</label>
										<div class="col-lg-12">
											<input class="form-control" name="ename" value="${emp.ename}" type="text"/>
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-lg-2">직종</label>
										<div class="col-lg-12">
											<input class="form-control" name="job" value="${emp.job}" type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label class="control-label col-lg-2">사수 사번</label>
										<div class="col-lg-12">
											<input class="form-control" name="mgr" value="${emp.mgr}"
												type="number" />
										</div>
									</div>
									
									
									<div class="form-group ">
										<label class="control-label col-lg-2">입사일</label>
										<div class="col-lg-12">
											<input class="form-control" name="hiredate" id="hiredate" value="${emp.hiredate}" type="text"/>
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-lg-2">월급</label>
										<div class="col-lg-12">
											<input class="form-control" name="sal" value="${emp.sal}" type="number" />
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-lg-2">수당</label>
										<div class="col-lg-12">
											<input class="form-control" name="comm" value="${emp.comm}" type="number" />
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-lg-2">부서번호</label>
										<div class="col-lg-12">
											<input class="form-control" name="deptno" value="${emp.deptno}" type="number" />
										</div>
									</div>
									
									<div class="form-group" style="margin:50px auto;">
										<div class="col-lg-12 col-md-3 col-md-offset-3">
											<button class="btn btn-success" type="submit">커밋하기</button>
											<a class="btn-get-started scrollto" href='ShowEmpList.member'>사원 리스트로 이동</a>
										</div>
									</div>
								</form><!-- //Form -->
								
							</div>
						</div>
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

