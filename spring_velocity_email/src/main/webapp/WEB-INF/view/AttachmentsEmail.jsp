<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring MVC Email Example</title>
        <style type="text/css">
            #sendEmailBtn {
                float: left;
                margin-top: 22px;
            }
        </style>
    </head>
 
    <body>
        <center>
        <h1>Spring MVC - Send e-mail with attachments</h1>
        <form method="post" action="" enctype="multipart/form-data">
            <table border="0" width="80%">
                <tr>
                    <td>Email To:</td>
                    <td><input type="text" name="receiverMail" size="65" /></td>
                </tr>
                <tr>
                    <td>Subject:</td>
                    <td><input type="text" name="subject" size="65" /></td>
                </tr>
                <tr>
                    <td>Message:</td>
                    <td><textarea cols="50" rows="10" name="message"></textarea></td>
                </tr>
                <tr>
                    <td>Attach file 1:</td>
                    <td><input type="file" name="attachment" size="60" /></td>
                </tr>        
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Send E-mail" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>