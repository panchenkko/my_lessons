<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title></title>
</head>
<body style="margin: 10px;">
<form action="${pageContext.servletContext.contextPath}/client/create" method="POST">
	<table>
		<tr>
			<td align="right" >Имя клиента : </td>
			<td>
				<input type="text" name="nameClient">
			</td>
		</tr>
		<tr>
			<td align="right" >Тип питомца : </td>
			<td>
				<input type="text" name="petType">
			</td>
		</tr>
		<tr>
			<td align="right" >Имя питомца : </td>
			<td>
				<input type="text" name="name">
			</td>
		</tr>
		<tr>
			<td align="right" >Возраст питомца : </td>
			<td>
				<input type="text" name="age">
			</td>
		</tr>
		<tr>
			<td><input type="submit" align="center" value="Submit"/></td>
		</tr>
	</table>
</form>
</body>
</html>