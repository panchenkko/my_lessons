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
    <%--<link href="${pageContext.servletContext.contextPath}/resources/css/selectRepositoryStyle.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/selectRepositoryStyle.css"/>">

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
            <h1>Выберите хранилище: </h1>
        </header>

        <hr />

        <form action="${pageContext.servletContext.contextPath}/selectRepository" method="POST">
            <div class="form-group">
                <input id="memory" type="radio" name="storage" value="memory" checked>
                <label for="memory">Memory Storage</label>
                    <br />
                <input id="jdbc" type="radio" name="storage" value="jdbc">
                <label for="jdbc">JDBC Storage</label>
                    <br />
                <input id="hibernate" type="radio" name="storage" value="hibernate">
                <label for="hibernate">Hibernate Storage</label>
                    <br />
                <input id="hibernateTemplate" type="radio" name="storage" value="hibernatetemplate">
                <label for="hibernateTemplate">Hibernate Template Storage</label>
                    <br />
                <input type="submit" id="button" class="btn btn-primary input-control" value="Готово">
            </div>
        </form>
    </div>
</body>
</html>
