<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Клиника домашних животных</title>

    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/css/selectLoggingStyle.css" rel="stylesheet">
    <%--<link href="<c:url value="/css/selectRepositoryStyle.css" />" rel="stylesheet">--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="js/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.1.min.js"></script>
</head>
<body>
<div class="container">
    <header>
        <h1>Выберите уровень логов: </h1>
    </header>

    <hr />

    <div class="form-group">
        <div class="column">
            <a id="info" href="${pageContext.servletContext.contextPath}/resources/html/logging/log4j_html_info.html">INFO</a>
                <br />
            <a id="warn" href="${pageContext.servletContext.contextPath}/resources/html/logging/log4j_html_warn.html">WARN</a>
                <br />
            <a id="error" href="${pageContext.servletContext.contextPath}/resources/html/logging/log4j_html_error.html">ERROR</a>
                <br />
            <a id="fatal" href="${pageContext.servletContext.contextPath}/resources/html/logging/log4j_html_fatal.html">FATAL</a>
        </div>
            <br />
        <a href="${pageContext.servletContext.contextPath}/client/index" id="returnBtn">
            <button class="btn btn-primary">Вернуться</button>
        </a>
    </div>
</div>
</body>
</html>
