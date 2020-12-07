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
<script type="text/javascript">

	$(document).ready(function(){
		
		//jasonview방식 ajax
		$('#ajaxBtn').click(function(){
			 $.ajax(
					 { 
						type : "post",
						url  : "KmListAjax.member",
						success : function(data){ 
							console.log(data);
							$('#exp').empty();
							$('#exp').append('<fieldset><legend>jsonview 방식</legend><p>View 객체가 Json객체로 파싱되어서 넘어온다</p></fieldset>');
							createTable(data.emp, "jsonview");
						} 
					 } 
			       )    
		});
		
		//삭제
		$(document).on("click",".delete",function(){
			$.ajax({
				type : "post",
				url  : "DeleteKmListAjax.member",
				data : {id : $(this).attr("value2")},
				success : function(data){ 
					createTable(data.koreamember, "삭제완료");
				} 
			})
		});


		$('#search').keyup(function(){
			  $.ajax(
					 {  
						type : "post",
						url  : "searchKmListAjax.member",
						data : { id :$('#search').val()},
						success : function(data){
							if(data.koreamember.length===0 || $("#search").val()===''){
								$('#menuView').empty();
								}
							else{
								$('#menuView').empty();
								createTable(data.koreamember, "jsonview");
								}
						} 
					 } 
			       )   
		});	
		
	})
	
	//수정 폼
		function empupdate(me){
				var tr = $(me).closest('tr')
				var datas = {id:tr.children().html()};
				tr.empty();
				
				$.ajax({
					type : "get",
					url:"UpdateKmListAjax.member",
					data:datas,
					success : function(data) {
					 	var td = "<td><input type='text' value='"+data.koreamember.id +"' readonly></td>";
							td +="<td><input type='text' value='"+data.koreamember.pwd +"' readonly></td>";
							td +="<td><input type='text' value='"+data.koreamember.name +"'></td>";
							td +="<td><input type='text' value='"+data.koreamember.age +"'></td>";
							td +="<td><input type='text' value='"+data.koreamember.gender) +"' readonly></td>";
							td +="<td><input type='text' value='"+data.koreamember.email +"'></td>";
							td +="<td><input type='text' value='"+data.koreamember.ip +"'></td>";
							td +="<td colspan='2'><input type='button' class='btn btn-success' onclick='kmlistupdateconfirm(this)' value='완료' value2="+data.emp.empno+" /></td>";
							$(tr).append(td); 
					}
				})
			}
		
		
		function kmlistupdateconfirm(me){			
			kmlistupdateok(me);
		}
		//수정 처리
		function kmlistupdateok(me){
			var tr = $(me).closest('tr');
			var data = {id:tr.find("td:eq(0)").children().val(),
						pwd:tr.find("td:eq(1)").children().val(),
						name:tr.find("td:eq(2)").children().val(),
						age:tr.find("td:eq(3)").children().val(),
						gender:tr.find("td:eq(4)").children().val(),
						email:tr.find("td:eq(5)").children().val(),
						ip:tr.find("td:eq(6)").children().val(),
					   };
			$.ajax({
				type : "post",
				url:"UpdateKmListAjax.member",
				data:data,
				success : function(data){  
					createTable(data.koreamember, "수정완료");
				} 
			})
		}
		
		//Json 전용 table 생성
		function createTable(data, way){
			$('#menuView').empty();
			var opr="<table id='fresh-table' class='table table-bordered'><thead><tr>"+
			    "<th>ID</th>"+
            	"<th>PWD</th>"+
            	"<th>NAME</th>"+
            	"<th>AGE</th>"+
            	"<th>GENDER</th>"+
            	"<th>EMAIL</th>"+
            	"<th>IP</th>"+
            	"<th>EDIT</th><th>DELETE</th></tr></thead><tbody>";
			$.each(data,function(index,koreamember){
				opr += "<tr><td>"+koreamember.id+
				"</td><td>"+koreamember.pwd+
				"</td><td>"+koreamember.name+
				"</td><td>"+koreamember.age+
				"</td><td>"+koreamember.gender+
				"</td><td>"+koreamember.email+
				"</td><td>"+koreamember.ip+
				"</td><td><input type='button' class='btn btn-success' onclick='kmlistpupdate(this)' value='수정' class ='update'  value2="+koreamember.id+
				"></td><td><input type='button' value='삭제' class ='delete btn btn-danger' value2="+koreamember.id+"></td></tr>";
			});
			opr+="<tr><td colspan='10'><input class='btn btn-success' type='button' onclick='createinput(this)' value='추가'></td></tr></tbody></table>";
			$('#menuView').append(opr);
		}
		
		//등록 폼
		function createinput(me){
			var tr = $(me).closest('tr');
			tr.empty();
			var td = "<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input type='text'></td>";
			td +="<td><input class='btn btn-success' type='button'onclick='empinsert(this)' value='완료'/></td>";
			td +="<td><input class='btn btn-danger' type='button'onclick='cancel(this)' value='취소'/></td>";
			$(tr).append(td); 
		
		}
		
		//취소버튼
		function cancel(me){
			var tr = $(me).closest('tr');
			tr.empty();
			tr.append("<td colspan='10'><input class='btn btn-success' type='button' onclick='createinput(this)' value='추가'></td>");
			
		}
		//등록 처리
		function empinsert(me){
			var tr = $(me).closest('tr');
			var data = {empno:tr.find("td:eq(0)").children().val(),
						ename:tr.find("td:eq(1)").children().val(),
						job:tr.find("td:eq(2)").children().val(),
						mgr:tr.find("td:eq(3)").children().val(),
						hiredate:tr.find("td:eq(4)").children().val(),
						sal:tr.find("td:eq(5)").children().val(),
						comm:tr.find("td:eq(6)").children().val(),
						deptno:tr.find("td:eq(7)").children().val(),
					   };
			$.ajax({
				type : "post",
				url:"insertAjax.member",
				data:data,
				success : function(data){  
					createTable(data.emp, "추가");
				} 
			})
		}
</script>
</head>
<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>

	<section id="hero">
		<div class="container">

			<div class="form-group">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="d-flex justify-content-end">
							<label for=""> 
								<input type="text" id="search" name="ename"
									class="form-control" placeholder="이름">
							</label>&nbsp;
						</div>
					</div>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading">
							<h4><input type="button" class='btn btn-success' value="회원정보" id="ajaxBtn"></h4>
						</header>
						<div class="table-responsive">
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