<html lang="en">
<head>
<meta charset="UTF-8">
<title>Summernote with Bootstrap 4</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<div class="bg-content">
		<div class="container" style="">

			<h3>Send email with SummerNote</h3>

			<form action="" method="post">
				<table class="table">

					<!-- <tr>
						<th>Sender</th>
						<td><input name="senderMail" class="form-control"></td>
					</tr>
					
					<tr>
						<th>Sender Name</th>
						<td><input name="senderName" class="form-control"></td>
					</tr> -->

					<tr>
						<th>Receiver</th>
						<td><input name="receiverMail" class="form-control"></td>
					</tr>


					<tr>
						<th>Title</th>
						<td><input name="subject" class="form-control"></td>
					</tr>


					<tr>
						<th>Message</th>
						<td><textarea name="message" class="form-control"
								id="message"></textarea> <!-- include summernote-ko-KR --> <script
								src="/summernote/lang/summernote-ko-KR.js"></script> <script
								type="text/javascript">
									$(document).ready(function() {
										//아이디가 content 에 서머노트를 적용 한다.
										$('#message').summernote({
											height : 300,
											lang : 'ko-KR' // default: 'en-US'
										});
									});
								</script></td>
					</tr>




					<tr>
						<td colspan="2"><input type="submit" value="Send"
							class="btn btn-info" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
</html>
