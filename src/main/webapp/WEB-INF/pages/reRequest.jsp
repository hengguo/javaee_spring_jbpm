<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false"%>
<%@ include file="header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重新贴写申请请假</title>
  </head>
  
  <body>
  <form action="reRequestAction" method="post">
  		<c:forEach items="${leaveList}"  var="item">
  
  <label>申请人：${sessionScope.staffName}</label>
    <br/>
    申请时长：<input type="text" name="leaveLong" value="${item.leaveLong}"/><br/>
    申请原因：<textarea rows="3" cols="15" name="leaveContent">${item.leaveContent}</textarea>
    <input type="hidden" name="taskId" value="${item.taskId}"/>
    <input type="submit" value="提交"/>
	</c:forEach>
  </form>
  
  </body>
</html>