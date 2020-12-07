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
						url  : "EmpListAjax.member",
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
				url  : "DeleteEmpAjax.member",
				data : {empno : $(this).attr("value2")},
				success : function(data){ 
					createTable(data.emp, "삭제완료");
				} 
			})
		});


		$('#search').keyup(function(){
			  $.ajax(
					 {  
						type : "post",
						url  : "searchAjax.member",
						data : { name :$('#search').val()},
						success : function(data){
							if(data.emp.length===0 || $("#search").val()===''){
								$('#menuView').empty();
								}
							else{
								$('#menuView').empty();
								createTable(data.emp, "jsonview");
								}
						} 
					 } 
			       )   
		});	
		
	})
	
	//수정 폼
		function empupdate(me){
				var tr = $(me).closest('tr')
				var datas = {empno:tr.children().html()};
				tr.empty();
				
				$.ajax({
					type : "get",
					url:"UpdateEmpAjax.member",
					data:datas,
					success : function(data) {
					 	var td = "<td><input type='text' value='"+data.emp.empno +"' readonly></td>";
							td +="<td><input type='text' value='"+data.emp.ename +"'></td>";
							td +="<td><input type='text' value='"+data.emp.job +"'></td>";
							td +="<td><input type='text' value='"+data.emp.mgr +"'></td>";
							td +="<td><input type='text' value='"+data.emp.hiredate.substring(0,10) +"' readonly></td>";
							td +="<td><input type='text' value='"+data.emp.sal +"'></td>";
							td +="<td><input type='text' value='"+data.emp.comm +"'></td>";
							td +="<td><input type='text' value='"+data.emp.deptno +"'></td>";
							td +="<td colspan='2'><input type='button' class='btn btn-success' onclick='empupdateconfirm(this)' value='완료' value2="+data.emp.empno+" /></td>";
							$(tr).append(td); 
					}
				})
			}
		
		
		function empupdateconfirm(me){			
			empupdateok(me);
		}
		//수정 처리
		function empupdateok(me){
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
				url:"UpdateEmpAjax.member",
				data:data,
				success : function(data){  
					createTable(data.emp, "수정완료");
				} 
			})
		}
		
		//Json 전용 table 생성
		function createTable(data, way){
			$('#menuView').empty();
			var opr="<table id='fresh-table' class='table table-bordered'><thead><tr>"+
			    "<th>EMPNO</th>"+
            	"<th>ENAME</th>"+
            	"<th>JOB</th>"+
            	"<th>MGR</th>"+
            	"<th>HIREDATE</th>"+
            	"<th>SAL</th>"+
            	"<th>COMM</th>"+
            	"<th>DEPTNO</th>"+
            	"<th>EDIT</th><th>DELETE</th></tr></thead><tbody>";
			$.each(data,function(index,emp){
				opr += "<tr><td>"+emp.empno+
				"</td><td>"+emp.ename+
				"</td><td>"+emp.job+
				"</td><td>"+emp.mgr+
				"</td><td>"+emp.hiredate.substring(0,10)+
				"</td><td>"+emp.sal+
				"</td><td>"+emp.comm+
				"</td><td>"+emp.deptno+
				"</td><td><input type='button' class='btn btn-success' onclick='empupdate(this)' value='수정' class ='update'  value2="+emp.empno+
				"></td><td><input type='button' value='삭제' class ='delete btn btn-danger' value2="+emp.empno+"></td></tr>";
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