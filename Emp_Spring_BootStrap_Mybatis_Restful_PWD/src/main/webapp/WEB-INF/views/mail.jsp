<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
    body {margin:0 auto; padding:0;  font-family:'dotum', '돋움' ,sans-serif;  font-size:12px;  color:#616161; }
    .top_bg {background:top center no-repeat;}
    p {margin:0;}
</style>
</head>
 
<body>
<form action="${pageContext.request.contextPath}/mailsender.member" method="post">
<div style="text-align:center">
<input type="text" name="from" size="120" style="width:50%" placeholder="보내는사람" >
<input type="text" name="to" size="120" style="width:50%" placeholder="받는사람1">
<input type="text" name="to" size="120" style="width:50%" placeholder="받는사람2">
</div>
<table width="600px" cellpadding="0" cellspacing="0" border="0" style="margin:0 auto;">
<tbody>
    <tr>
        <td style="padding:0 20px; ">
            <table width="100%" cellpadding="0" cellspacing="0" border="0" >
            <thead style="background-color:#4c5a67">
                <tr height="40">
                    <th align="left" style="padding-left:25px; font-size:14px;"><font color="#ffffff"><b><input type="text" name="title" size="120" style="width:90%" placeholder="제목"></b></font></th>
                </tr>
            </thead>    
            <tbody>
                <tr>
                    <td align="left" height="77" valign="top" style="border-left:1px solid #7aa4b7; border-bottom:1px solid #7aa4b7; border-right:1px solid #7aa4b7; padding:28px 28px 20px 28px; background:url([_IMGURL_]/mail/conf_bg.gif) 98% center no-repeat;">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0" >
                        <tbody>
                            <tr>
                                <td width="200" height="200" valign="top"><font color="#006190"><textarea rows="30%" cols="100%" name="content"  placeholder="내용"></textarea> </font></td>
                               
                            </tr>
                
                        </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
            </table>
        </td>
    </tr>
</tbody>
</table>
 <div align="center">
      <input type="submit" value="메일 보내기" class="btn btn-warning">
      <select name="vm" id="cars">
    <option value="mail.vm">VM1</option>
    <option value="sample.vm">VM2</option>
  </select>
    </div>
</form>
</body>
</html>