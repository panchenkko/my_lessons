<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title></title>
</head>
<body style="margin: 10px;">
<form action="${pageContext.servletContext.contextPath}/client/edit" method="POST">
	<input type="hidden" name="id" value="${client.id}">
	<table>
		<tr>
			<td align="right" >Имя клиента : </td>
			<td>
				<input type="text" name="clientName" value="${client.name}" required>
			</td>
		</tr>
		<tr>
			<td align="right" >Тип питомца : </td>
			<td>
				<input type="text" name="petType" value="${client.pet.petType}">
			</td>
		</tr>
		<tr>
			<td align="right" >Имя питомца : </td>
			<td>
				<input type="text" name="petName" value="${client.pet.name}" required>
			</td>
		</tr>
		<tr>
			<td align="right" >Пол питомца : </td>
			<td>
				<select name="petSex">
					<option selected="selected" disabled>Пол питомца</option>
					<c:if test="${client.pet.petSex == 'Мужской'}">
						<option value="Мужской" selected>Мужской</option>
						<option value="Женский">Женский</option>
					</c:if>
					<c:if test="${client.pet.petSex == 'Женский'}">
						<option value="Мужской">Мужской</option>
						<option value="Женский" selected>Женский</option>
					</c:if>
					<c:if test="${client.pet.petSex != 'Мужской' && client.pet.petSex != 'Женский'}">
						<option value="Мужской">Мужской</option>
						<option value="Женский">Женский</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" >Возраст питомца : </td>
			<td>
				<input type="number" name="petAge" value="${client.pet.age}" min="0" max="300">
			</td>
		</tr>
		<tr>
			<td><input type="submit" align="center" value="Submit"/></td>
		</tr>
	</table>
</form>
</body>
</html>