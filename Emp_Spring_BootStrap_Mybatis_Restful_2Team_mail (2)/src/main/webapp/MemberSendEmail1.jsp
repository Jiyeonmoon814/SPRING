<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/common/SummerNote.jsp"></jsp:include>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script type="text/javascript">
 $(function() {
	$('#sendEmail').submit(validate);
});
	$(document).ready(function() {
		//아이디가 content 에 서머노트를 적용 한다.
		$('#summernote').summernote({
			height : 300,
			lang : 'ko-KR', // default: 'en-US'
			callbacks: { // 콜백을 사용
                // 이미지를 업로드할 경우 이벤트를 발생
			    onImageUpload: function(files, editor, welEditable) {
				    sendFile(files[0], this);
				}
			}
		});
	});

	$(function(){
		//, 로 분리해 하나의 입력 필드에 여러개의 값을 선택 가능
		 function split( val ) {
		      return val.split( /,*/s);
		    }
		    //term은 검색어 parameter 
		    function extractLast( term ) {
		      return split( term ).pop();
		    }
		function log(message) {
           //log에 prependTo 할 message를 정의
			$("<input  id='email' class='email' name='to' readonly>").val(message).prependTo("#log");
           //scroll 위치 설정
			$("#log").scrollTop(10);
			}
		$("#searchMail").autocomplete({
           //자동완성 JQuery UI
		      source: function( request, response ) {
		    	  $.ajax({
						type: 'get',
						url:"members/searchmail.member",
						data : { userid :$('#searchMail').val()},
                        //id값으로 DB 정보 추출
						success:function(data){
							response(
									$.map(data,function(item){
										return{
                                           //검색 시 Label에 보여질 값 id(email)
											label: item.userid+"("+item.email+")",
                                           //선택 시 출력되는 값을 id로 설정
											value: item.email,
											idx: item.testIdx
											};
										})
									);
						}
		    	  })
			      },
			      minLength:2,  // 몇글자 이상 입력되야 서버로 요청을 보낼지 결정 
			      select: function (event, ui){ //자동완성된 값 선택
			    	   log(ui.item.value); // 앞에서 정의한 message 값 
			    	    $( document ).ready( function() {	
			            	$( '#searchMail' ).val(""); // 선택이 끝나면 검색창 값은 빈값
			        	} );	
			    	  var terms =split(this.value);
			            // 현재 입력값 제거합니다.
			            terms.pop();
			            return false;
			      }			  
		})

	})
        //클릭 시 클릭 대상 제거
		$(document).on('click','input[class=email]',function(){
				$(this).remove();
			})
		
    /* summernote에서 이미지 업로드시 실행할 함수 */
 	function sendFile(file, editor) {
        // 파일 전송을 위한 폼생성
 		data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({
 	        data : data,
 	        type : "POST",
 	      	enctype: 'multipart/form-data',
 	        url : "summernote.member",
 	        cache : false,
 	        contentType : false,
 	        processData : false,
 	        success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력
 	        	$(editor).summernote('editor.insertImage', data.url);
 	        	$('#summernote').append('<img src="'+data.url+'"/>');
 	        },
 	  		error:function(request,status,error){
 		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 		   }

 	    });
 	}
	
	function validate() {
		if ($('#receiverMail').val() == "") { // 사원번호 검사
			alert('수신자 메일을 입력해 주세요.');
			$('#receiverMail').focus();
			return false;
		} else if ($('#subject').val() == "") { // 이름 검사
			alert('제목을 입력해 주세요.');
			$('#subject').focus();
			return false;
		} else if ($('#message').val() == "") { // 직종 검사
			alert('내용을 입력해 주세요.');
			$('#message').focus();
			return false;
		} 
	}

	

</script>

</head>
<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>
	<c:set var="usermail" value="${requestScope.usermail}" />
	<section id="hero">
		<div class="container">
			<!-- 회원가입 Form -->
			<div class="row">
				<div class="col-md-12 col-lg-12 ">
					<section class="panel">
						<div class="panel-body">
							<div class="form">

								<!-- 회원 정보 수정하는 Form엘리먼트 속 데이터 -->
								<form action="${pageContext.request.contextPath}/userMailSend.member" id="sendEmail"
									class="form-validate form-horizontal" method="post"
									enctype="multipart/form-data">

									<div class="form-group ">
										<label for="receiverMail" class="control-label col-lg-2" id="email">
											검색 </label>
																				
										<div class="col-lg-12">
									
											<input class="form-control" id="searchMail" type="text" ></input>
											<!-- <textarea id="searchMail"> </textarea> -->
									
										</div>
										<br>
										<label for="receiverMail" class="control-label col-lg-2">
											받는사람 </label>
										<div id="log" class="form-control" style="height: 50px; class="ui-widget-content">
											
										</div>
										</div>
									</div>
									
									<div class="form-group ">
										<label for="subject" class="control-label col-lg-2">
											제목 </label>
										<div class="col-lg-12">
											<input class="form-control" name="title" id="subject" type="text" />
											
										</div>
									</div>

									<div class="form-group ">
										<label for="attachment" class="control-label col-lg-2">
											파일첨부
										</label>
										<div class="col-lg-12">
											<input  name="attachment" id="attachment" type="file" />
										</div>
									</div>

									<div class="form-group">
										<label for="message" class="control-label col-lg-2">
											내용
										</label>
										<div class="col-lg-12">
											<textarea class="form-control" class="form-control" name="content" id="summernote"></textarea>
										</div>
									</div>

									<div class="form-group" style="margin: 50px auto;">
										<div class="col-lg-12 col-md-3 col-md-offset-3">
										 <select name="vm" id="Velocity">
  										  <option value="usermail.vm">VM1</option>
    									   <option value="sample.vm">VM2</option>
 										 </select>
											<button class="btn btn-primary" type="submit">보내기</button>
											<a class ="btn btn-success" href="javascript:history.go(-1)">뒤로가기</a>
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