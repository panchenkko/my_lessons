<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Клиника домашних животных</title>

    <%--<link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${pageContext.servletContext.contextPath}/resources/css/indexStyle.css" rel="stylesheet">--%>

    <%--<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
    <%--<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="js/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="js/respond/1.4.2/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
</head>
<body>
	<div class="container">
        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <div class="form-group">
                <input type="tel" name="binary" pattern="^[0-1]+$" placeholder=" Input binary number" required>
                <input type="submit" id="button" class="btn btn-primary input-control" value="Transfer">
            </div>
        </form>
    </div>
    <c:if test="${!transfers.isEmpty()}">
        <hr />
        <div class="body">
            <table border="2" class="table">
                <caption>All Actions</caption>
                <tbody>
                <tr>
                    <th>Date and time</th>
                    <th>Binary code</th>
                    <th>Decimal code</th>
                </tr>
                <div class="createClient">
                    <c:forEach items="${transfers}" var="transfer" varStatus="status">
                        <tr>
                            <c:if test="${transfer.getDate() != null}">
                                <td>${transfer.date}</td>
                            </c:if>
                            <c:if test="${transfer.getDate() == null}">
                                <td> - </td>
                            </c:if>
                            <td>${transfer.binary}</td>
                            <td>${transfer.decimal}</td>
                        </tr>
                    </c:forEach>
                </div>
                </tbody>
            </table>
        </div>
    </c:if>
    <%--<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>--%>
</body>
</html>