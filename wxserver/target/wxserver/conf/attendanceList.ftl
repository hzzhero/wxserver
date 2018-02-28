<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/attendance/preAdd.html">添加</a>
		<table>
			<tr>
				<th>ID</th>
				<th>CARD_ID</th>
				<th>CAPTURE_TIME</th>
				<th>POS_ID</th>
			</tr>
		<#list  attendances as attendance >
			<tr>
				<td>${attendance.id}</td>
				<td>${attendance.cardId}</td>
				<td>${attendance.captureTime}</td>
				<td>${attendance.posId}</td>
			</tr>
		</#list>
		</table>
</body>
</html>