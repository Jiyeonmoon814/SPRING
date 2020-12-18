<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<!-- script & Css CDN를 묶은 Head.jsp -->
<jsp:include page="/common/Head.jsp"></jsp:include>
</head>
<body>
	<!-- Top 부분 header와 nav를 묶은 Top.jsp -->
	<jsp:include page="/common/Top.jsp"></jsp:include>

	<section id="hero">
		<div class="container">

				<figure class="highcharts-figure">
				
					<div id="container" style="min-height:600px;">
						<!--div id="container" 차트 구간 -->
					</div>
					
				</figure>
		</div>
	</section>
	<script type="text/javascript">
$(function(){
	var empname =[];
	var empcomm =[];
	var empsal=[];
		$.getJSON("<%=application.getContextPath()%>/Ex10_Jsonlib_emplist.jsp", function(data) {

								$.each(data, function(index, obj) {

									empname.push(obj.ename);
									empcomm.push(parseInt(obj.comm) / 10);
									empsal.push(parseInt(obj.sal) / 10);
								});//each

								Highcharts.chart('container',
												{
													chart : {
														type : 'column'
													},
													title : {
														text : '직원별 월급 및 수당'
													},
													subtitle : {
														text : '수당:한달동안 야근,특근 수당'
													},
													xAxis : {
														categories : empname, //x축 항목
														crosshair : true
													},
													yAxis : {
														min : 0,
														title : {
															text : '만원'
														}
													},
													tooltip : {
														headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
														pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
																+ '<td style="padding:0"><b>{point.y:.1f} 만원</b></td></tr>',
														footerFormat : '</table>',
														shared : true,
														useHTML : true
													},
													plotOptions : {
														column : {
															pointPadding : 0.2,
															borderWidth : 0
														}
													},
													series : [ {
														name : '월급',
														data : empsal

													}, {
														name : '수당',
														data : empcomm

													} ]
												});
							});

		});
	</script>
	<!-- Footer부분의 jsp -->
	<jsp:include page="/common/Bottom.jsp"></jsp:include>
	<!-- Template 자체 Animation 및 Vendor 파일을 묶은 Foot.jsp -->
	<jsp:include page="/common/Foot.jsp"></jsp:include>
</body>
</html>