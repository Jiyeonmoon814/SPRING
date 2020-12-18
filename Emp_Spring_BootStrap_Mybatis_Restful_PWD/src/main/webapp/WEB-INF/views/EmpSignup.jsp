<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>


<script type="text/javascript">
$(function() {
	   $("#hiredate").datepicker({dateFormat : "y/mm/dd"});
	 }); 
$('#hiredate').datepicker("option","onClose",function(selectedDate){
	//selectedDate 선택한 날짜를 받는 변수
	console.log(selectedDate);
	//div 달력
	$('#dates').datepicker("option","minDate",selectedDate);
	$('#totaldates').text(calcDates());
	
});
 $(function() {
	$('#joinForm').submit(validate);
	$("#empnocheck").click(function() {
							if ($("#empno").val() === "") {
								alert("사원번호를 입력하세요.");
							} else {
								$.ajax({
									url : "IdCheck",
									type : "POST",
									dataType : "html",
									data : {
										data : $("#empno").val()
									},
									success : function(responsedata) {
										if (responsedata === "true") {
											alert("존재하는 사원번호 입니다. 다시 입력해주세요.");
											$("#empno").val('').focus();
											$("#empnocheck").val('중복검사');
											} else {
											alert("사용가능한 사원번호입니다.");
											$("#ename").focus();
											$("#empnocheck").val("사용가능한 사원번호 입니다.");
											
											//받은 데이터가 false
										}
									}
							});
						}
				});
	
	$(document).on('click', '#upload', function(e) {
		console.log($("#fileName").val());
		$.ajax({
			url : "upload.member",
			type : 'POST',
			dataType : "json",
			data : {
				filename : $("#fileName").val()
			},
			success : function(data) {
				
			}
		});
	})
});
 
	function validate() {
		if ($('#empno').val() == "") { // 사원번호 검사
			alert('사원번호를 입력해 주세요.');
			$('#empno').focus();
			return false;
		} else if ($('#empnocheck').val() == "중복검사") { // 이름 검사
			alert('사원번호 중복검사를 해주세요');
			$('#empno').focus();
			return false;
		}else if ($('#ename').val() == "") { // 이름 검사
			alert('사원이름을 입력해 주세요.');
			$('#ename').focus();
			return false;
		} else if ($('#job').val() == "") { // 직종 검사
			alert('직종을 입력해 주세요.');
			$('#job').focus();
			return false;
		} else if ($('#mgr').val() == "") { // 매니저 번호검사
			alert('매니저번호를 입력해 주세요.');
			$('#mgr').focus();
			return false;
		} else if ($('#hiredate').val() == "") { // 입사일
			alert('입사일을 입력해 주세요.');
			$('#hiredate').focus();
			return false;
		} else if ($('#sal').val() == "") { // 입사일
			alert('월급을 입력해 주세요.');
			$('#sal').focus();
			return false;
		} else if ($('#comm').val() == "") { // 입사일
			alert('수당을 입력해 주세요.');
			$('#comm').focus();
			return false;
		}
	}
</script>
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
							<h1>신규 사원 등록</h1>
						</header>
						<div class="panel-body">
							<div class="form">

								<!-- 회원 정보 수정하는 Form엘리먼트 속 데이터 -->
								<form action="upload.member" id="joinForm"
									class="form-validate form-horizontal" method="post"
									enctype="multipart/form-data">

									<div class="mb-3">
										<label for="title">사진 등록</label> <br> <img id="preview"
											src="upload/emp.png" width="300" alt="로컬에 있는 이미지가 보여지는 영역">
										<input type="file" id="fileName" name="file"
											class="fileName" accept="image/*">

									</div>
									<div class="form-group ">
										<label for="empno" class="control-label col-lg-2">
											사원번호(EMPNO) </label>
										<div class="col-lg-12">
											<input class="form-control" name=empno id="empno" type="text" />
											<input class="form-control mt-5" type="button" value="사원 번호 중복검사"
												id="empnocheck" style="background: skyblue">

										</div>
									</div>

									<div class="form-group ">
										<label for="ename" class="control-label col-lg-2">사원이름(ENAME)
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="ename" id="ename"
												type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="job" class="control-label col-lg-2">
											직종(JOB) </label>
										<div class="col-lg-12">
											<input class="form-control" name="job" id="job" type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="mgr" class="control-label col-lg-2">
											매니저번호(MGR) <span class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="mgr" id="mgr" type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="hiredate" class="control-label col-lg-2">
											입사일(HIREDATE)<span class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="hiredate" id="hiredate"
												type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="sal" class="control-label col-lg-2">월급(SAL)<span
											class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="sal" id="sal" type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="comm" class="control-label col-lg-2">수당<span
											class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="comm" id="comm" type="text" />
										</div>
									</div>

									<div class="form-group ">
										<label for="DEPTNO" class="control-label col-lg-2">
											부서번호<span class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input type="radio" name="deptno" id="deptno" value="10">10
											<input type="radio" name="deptno" id="deptno" value="20">20
											<input type="radio" name="deptno" id="deptno" value="30">30
										</div>
									</div>



									<div class="form-group" style="margin: 50px auto;">
										<div class="col-lg-12 col-md-3 col-md-offset-3">
											<button class="btn btn-primary" type="submit">회원가입
												요청</button>
											<input type="reset" value="모두 비우기" />
										</div>
									</div>
								</form>
								<!-- //Form -->

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