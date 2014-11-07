<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>申请请假</title>
</head>

<body>
	<form action="requestAction" method="post">
		<label>申请人：${sessionScope.staffName}</label> <br /> 申请时长：<input
			type="text" name="leaveLong"><br /> 申请原因：
		<textarea rows="3" cols="15" name="leaveContent"></textarea>

		<input type="submit" value="提交" />
	</form>
</body>
</html>
