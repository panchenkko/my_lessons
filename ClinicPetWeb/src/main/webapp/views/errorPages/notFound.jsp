<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Поиск клиента</title>

    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/css/notFound.css" rel="stylesheet">

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
        <div>
            <h1>Ничего не найдено :(</h1>
            <p class="lead"><span style="color: red; font-style: italic">Приносим извинения.</span><br>Можете вернуться на главную страницу:</p>
        </div>
        <a href="${pageContext.servletContext.contextPath}/client/index" id="returnBtn">
            <button class="btn btn-primary">Вернуться</button>
        </a>
        <%--<img src="../img/jpg8.jpg" alt="">--%>
    </header>
</div>
<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
