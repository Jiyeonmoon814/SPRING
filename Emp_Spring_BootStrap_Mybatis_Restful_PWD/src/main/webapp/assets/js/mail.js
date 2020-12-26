 $(function() {
	$('#joinForm').submit(validate);
	$("#useridcheck").click(function() {
							if ($("#userid").val() === "") {
								alert("아이디를 입력하세요.");
							} else {
								$.ajax({
									url : "members/Idcheck.member",
									type : "get",
									//dataType : "html",
									data : {
										userid : $("#userid").val()
									},
									success : function(responsedata) {
										console.log(responsedata);
										if (responsedata === "true") {
											alert("존재하는 아이디 입니다. 다시 입력해주세요.");
											$("#userid").val('').focus();
											$("#useridcheck").val('중복검사');
											} else {
											alert("사용가능한 아이디입니다.");
											$("#userid").focus();
											$("#useridcheck").val("사용가능한 아이디입니다.");
											
											//받은 데이터가 false
										}
									}
							});
						}
				});


	$("#mailcheck").click(function() {
		if ($("#email").val() === "") {
			alert("이메일을 적어주세요");		
		}$.ajax({
		type:"POST",
		url:"mailSend.member",
		data:{
			mail : $("#email").val()
			},
		success: function(data){
			$('#mailsend').empty();
					$('#mailsend').text("메일이 발송되었습니다. 확인해 주세요");
					$('#mailsend').css({
						"color":"black",
						"font-size":"12px"
					});
			},error: function(e){
			
				}

		});
	}); 
	
	$("#codeCheck").click(function() {
		if ($("#code").val() === "") {
			alert("인증번호를 적어주세요");
		}$.ajax({
		type:"POST",
		url:"mailCheck.member",
		data:{
			mail : $("#email").val(), code:$("#code").val()
			},
		success: function(result){
			console.log(result);
			if(result === "true"){
				$('#alert').empty();
				$('#alert').text("인증되었습니다.");
				$('#alert').css({
					"color":"blue",
					"font-size":"12px"
				})
				$('#mailsuccess').val('true');
				}else{
					$('#alert').empty();
					$('#alert').text("인증에 실패하였습니다. 다시 입력해주세요");
					$('#alert').css({
						"color":"red",
						"font-size":"12px"
					});
					$("#code").val('');
					$("#code").focus();
					}
			},error: function(e){
			
				}

		});
	});
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

	function validate() {
		if ($('#userid').val() == "") { // 사원번호 검사
			alert('아이디를 입력해 주세요.');
			$('#userid').focus();
			return false;
		} else if ($('#useridcheck').val() == "중복검사") { // 이름 검사
			alert('아이디 중복검사를 해주세요');
			$('#userid').focus();
			return false;
		}else if ($('#pwd').val() == "") { // 이름 검사
			alert('비밀번호를 입력해 주세요.');
			$('#password').focus();
			return false;
		} else if ($('#name').val() == "") { // 직종 검사
			alert('성함을 입력해 주세요.');
			$('#name').focus();
			return false;
		} else if ($('#age').val() == "") { // 매니저 번호검사
			alert('나이를 입력해 주세요.');
			$('#age').focus();
			return false;
		} else if ($('#gender').val() == "") { // 입사일
			alert('성별을 입력해 주세요.');
			$('#gender').focus();
			return false;
		} else if ($('#email').val() == "") { // 입사일
			alert('이메일을 입력해 주세요.');
			$('#email').focus();
			return false;
		}else if ($('#mailsuccess').val() == "false") { // 입사일
			alert('메일인증을 해주세요');
			$('#code').focus();
			return false;
		}
	}