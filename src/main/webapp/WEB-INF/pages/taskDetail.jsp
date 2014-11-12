<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page isELIgnored ="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>具体信息审核页面</title>

  </head>
  
  <body>
  	<c:forEach items="${leaveList}"  var="item">
    	<p>请假申请人：${staff.staffName}</p>
    	<p>请假时间:${item.leaveLong}</p>
    	<p>请假原因：${item.leaveContent}</p>
    	<p>目前状态：${item.leaveState}</p>
	</c:forEach>
    <form action="examAction" method="post">
    	<input type="hidden" name="taskId" value="${taskId}"/>
    	<input type="submit" name="result" value="批准"/>
    	<input type="submit" name="result" value="驳回"/>
    </form>
  </body>
</html>
