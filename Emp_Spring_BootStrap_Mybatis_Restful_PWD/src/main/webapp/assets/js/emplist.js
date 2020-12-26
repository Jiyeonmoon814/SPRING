$(document).ready(function(){
	//$('#restconBtn').click(function(){
		 $.ajax(
				 {  
					type : "GET",
					url  : "members.member",
					success : function(data){
						console.log(data); 
						createTable(data);
					} 
				 } 
		       )    
	//});

       $('#search').keyup(function(){
    		  $.ajax(
    				 {  
    					type : "post",
    					url  : "members/search.member",
    					data : { userid :$('#search').val()},
    					success : function(data){
    						console.log(data);
    							$('#menuView').empty();
    							createTable(data);
    					} 
    				 } 
    		       )   
    	});	
}); //end dom




function createTable(data){
	$('#menuView').empty();
	var opr="<table id='fresh-table' class='table'><thead><tr>"+
	    "<th>아이디</th>"+
	    "<th>비밀번호</th>"+
    	"<th>이름</th>"+
    	"<th>나이</th>"+
    	"<th>성별</th>"+
    	"<th>이메일</th>"+
    	"<th>메일보내기</th><th>수정</th><th>삭제</th></tr></thead><tbody>";
	$.each(data,function(index,member){
		opr += "<tr><td>"+member.userid+
		"</td><td>"+"암호화"+
		"</td><td>"+member.name+
		"</td><td>"+member.age+
		"</td><td>"+member.gender+
		"</td><td>"+member.email+
		"</td><td><input type='button' onclick='memberupdate(this)' class='btn btn-success' value='메일보내기' class ='update' value2="+member.userid+
		"></td><td><input type='button' onclick='memberupdate(this)' class='btn btn-success' value='수정' class ='update' value2="+member.userid+
		"></td><td><input type='button' value='삭제' value2="+member.userid+" onclick='memberdelete(this)' class ='delete btn btn-danger'></td></tr>";
	});
	//opr+="<tr><td colspan='10'><input type='button' onclick='createinput(this)' class='btn btn-success' value='추가'></td></tr></tbody></table>";
	$('#menuView').append(opr);
};


function memberdelete(me){
	var tr = $(me).closest('tr')
	var datas = {userid:tr.children().html()};
	var ajaxurl = "members/" + tr.children().html()+".member";
	console.log(ajaxurl);
	// tr.children().html() == EMPNO

	$.ajax({
		type : "DELETE",
		url:ajaxurl, 
		success : function(data) {
			alert(data);
			$.ajax(
					 {  
						type : "GET",
						url  : "members.member",
						success : function(data){ 
	
							createTable(data);
						} 
					 } 
			       ) 
		}
	})
}

function memberupdate(me){
	var tr = $(me).closest('tr')
	var datas = {userid:tr.children().html()};
	var ajaxurl = "members/" + tr.children().html() + "/update.member";
	console.log(tr.children().html());
	tr.empty();

	$.ajax({
		type : "get",
		url:ajaxurl, 
		success : function(data) {
			console.log(data);
		 	var td = "<td><input type='text' value='"+data.userid +"' disabled></td>";
		 		td +="<td><input type='text' value='"+data.pwd +"' disabled></td>";
				td +="<td><input type='text' value='"+data.name +"'></td>";
				td +="<td><input type='text' value='"+data.age +"'></td>";
				td +="<td><input type='text' value='"+data.gender +"'></td>";
				td +="<td><input type='text' value='"+data.email +"'></td>";
				td +="<td colspan='2'><input type='button'onclick='memberupdateconfirm(this)' class='btn btn-success' value='완료' value2="+data.userid+"  /></td>";
				$(tr).append(td); 
		}
	})
}

function memberupdateconfirm(me){			
	memberupdateok(me);
}


function memberupdateok(me){
    var tr = $(me).closest('tr');

    var memberinfo = new Object();

    var tr = $(me).closest('tr')
    var datas = {userid:tr.children().html()};
    console.log(tr.find("td:eq(0)").children().val());
    
    
    var uri = "members/" +tr.find("td:eq(0)").children().val()+".member";
 	// tr.children().html() == EMPNO
 	
    memberinfo.userid = tr.find("td:eq(0)").children().val();
    memberinfo.pwd = tr.find("td:eq(1)").children().val();
    memberinfo.name = tr.find("td:eq(2)").children().val();
    memberinfo.age = tr.find("td:eq(3)").children().val();
    memberinfo.gender = tr.find("td:eq(4)").children().val();
    memberinfo.email = tr.find("td:eq(5)").children().val();
    
    var jsoninfo = JSON.stringify(memberinfo);
    console.log(jsoninfo);
    
    $.ajax({
        type : "PUT",
        url: uri,
        data : jsoninfo,
        contentType: 'application/json;charset=UTF-8',
        success : function(result){
        	alert(result);
			$.ajax(
					 {  
						type : "GET",
						url  : "members.member",
						success : function(data){ 
	
							createTable(data);
						} 
					 } 
			       ) 
        }
    });
}

function memberinsert(me){
	var tr = $(me).closest('tr');
	var memberinfo = new Object();
	
	memberinfo.userid = tr.find("td:eq(0)").children().val();
	memberinfo.pwd = tr.find("td:eq(1)").children().val();
	memberinfo.name = tr.find("td:eq(2)").children().val();
	memberinfo.age = tr.find("td:eq(3)").children().val();
	memberinfo.gender = tr.find("td:eq(4)").children().val();
	memberinfo.email = tr.find("td:eq(5)").children().val();
    
    var jsoninfo = JSON.stringify(memberinfo);
    console.log(jsoninfo);
    
	$.ajax({
		type : "post",
		url:"members/new.member",
		data:jsoninfo,
        contentType: 'application/json;charset=UTF-8',
		success : function(data){  
			alert(data);
			$.ajax(
					 {  
						type : "GET",
						url  : "members.member",
						success : function(data){ 
	
							createTable(data);
						} 
					 } 
			       ) 
		} 
	})
}

function createinput(me){
	var tr = $(me).closest('tr');
	tr.empty();
	var td = "<td><input type='text'></td>";
	td +="<td><input type='text'></td>";
	td +="<td><input type='text'></td>";
	td +="<td><input type='text'></td>";
	td +="<td><input type='text'></td>";
	td +="<td><input type='text'></td>";
	td +="<td><input type='button'onclick='memberinsert(this)' class='btn btn-success' value='완료'/></td>";
	td +="<td><input type='button'onclick='cancel(this)' class ='delete btn btn-danger' value='취소'/></td>";
	$(tr).append(td); 

}

//취소버튼
function cancel(me){
	var tr = $(me).closest('tr');
	tr.empty();
	tr.append("<td colspan='10'><input type='button' onclick='createinput(this)' class='btn btn-success' value='추가'></td>");
	
}