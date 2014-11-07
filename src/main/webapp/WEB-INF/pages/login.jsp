<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>普通用户登录页面</title>
  </head>
  
  <body> 
  	<form action="userLoginAction" method="post">
    	用户名:<input type="text" name="staffName"/><br>
    	密码:<input type="text" name="staffPsw"><br>
    	<input type="submit" value="提交" >
    	<input type="reset" value="重置">
    </form>
  </body>
</html>
