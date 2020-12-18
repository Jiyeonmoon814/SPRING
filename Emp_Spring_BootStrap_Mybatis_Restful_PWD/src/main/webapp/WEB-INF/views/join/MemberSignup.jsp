<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>
<script type="text/javascript" src="assets/js/mail.js"></script>


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
							<h1>신규 계정 등록</h1>
						</header>
						<div class="panel-body">
							<div class="form">

								<!-- 회원 정보 수정하는 Form엘리먼트 속 데이터 -->
								<form id="joinForm"
									class="form-validate form-horizontal" method="post"
									enctype="multipart/form-data">

									<div class="form-group ">
										<label for="userid" class="control-label col-lg-2">
											아이디(USERID) </label>
										<div class="col-lg-12">
											<input class="form-control" name="userid" id="userid" type="text" placeholder="아이디를 입력해 주세요"/>
											<input class="form-control mt-5" type="button" value="아이디 중복검사"
												id="useridcheck" style="background: skyblue">

										</div>
									</div>

									<div class="form-group ">
										<label for="password" class="control-label col-lg-2">
											비밀번호(PWD) </label>
										<div class="col-lg-12">
											<input class="form-control" name="pwd" id="pwd" type="password" placeholder="비밀번호를 입력해 주세요"/>
										</div>
									</div>

									<div class="form-group ">
										<label for="name" class="control-label col-lg-2">
											이름(NAME) <span class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="name" id="name" type="text" placeholder="이름를 입력해 주세요"/>
										</div>
									</div>

									<div class="form-group ">
										<label for="age" class="control-label col-lg-2">
											나이(AGE)<span class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="age" id="age"
												type="text" placeholder="나이를 입력해 주세요" />
										</div>
									</div>

									<div class="form-group ">
										<label for="gender" class="control-label col-lg-2">성별(GENDER)<span
											class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input name="gender" class="gender" id="gender" type="radio" value="male">남
											<input name="gender" class="gender" id="gender" type="radio" vaule="female">여
										</div>
									</div>

									<div class="form-group ">
										<label for="email" class="control-label col-lg-2">이메일(EMAIL)<span
											class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="email" id="email" type="text" placeholder="이메일을 입력해 주세요"/>
											<span id="mailsend"></span>
											<input class="form-control mt-5" type="button" value="인증번호 전송"
												id="mailcheck" style="background: skyblue">
											<input type="hidden" id="mailsuccess" value="false"/>
										</div>
									</div>
									<div class="form-group ">
										<label for="code" class="control-label col-lg-2">인증번호<span
											class="required">*</span>
										</label>
										<div class="col-lg-12">
											<input class="form-control" name="code" id="code" type="text" placeholder="인증번호를 입력해 주세요"/>
											<span id="alert"></span>
											<input class="form-control mt-5" type="button" value="인증번호 확인"
												id="codeCheck" style="background: skyblue">									
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