<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title></title>
</head>
<body style="margin: 10px;">
<table border="1" style="margin-bottom: 5px;">
	<caption style="margin-bottom: 1px;">
		<a href="${pageContext.servletContext.contextPath}/views/client/CreateClient.jsp">Добавить клиента</a>
	</caption>
	<tr>
		<th style="padding: 15px;">Список клиентов</th>
		<th style="padding: 15px;">Действия</th>
	</tr>
	<c:forEach items="${clients}" var="client" varStatus="status">
		<tr valign="top">
			<td style="padding: 10px;">
				<b>Имя клиента: </b>${client.name},
				<b>${client.pet.petType}: </b>${client.pet.name},
				<b>возраст: </b>${client.pet.age}
			</td>
			<td style="padding: 10px;">
				<a style="padding: 10px;"
				   href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">Редактировать</a>
				<a style="padding: 10px; border-left: 1px solid #000000;"
				   href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}">Удалить</a>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>