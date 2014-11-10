<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>待办任务</title>
</head>

<body>

	<table border="1" width="100%">
		<caption>待办任务</caption>
		<thead>
			<tr>
				<td>taskId</td>
				<td>taskName</td>
				
				<td>assignee</td>
				<td>查看详情并处理</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${taskList}" var="item">

				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.assignee}</td>
					<td><a href="${item.formResourceName}?taskId=${item.id}">查看具体信息</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
