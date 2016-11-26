<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Calculation</title>

    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/css/pageCalcStyle.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="js/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.1.min.js"></script>

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/pageCalcValidation.js"></script>
</head>
<body>
    <div class="container">
        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <div class="form-group">

                <input id="binary" type="radio" name="format" value="binary" checked onclick="saveFormat(this.value)">
                <label for="binary">Binary</label>

                <input id="octal" type="radio" name="format" value="octal" onclick="saveFormat(this.value)">
                <label for="octal">Octal</label>

                <input id="decimal" type="radio" name="format" value="decimal" onclick="saveFormat(this.value)">
                <label for="decimal">Decimal</label>

                <input id="hexadecimal" type="radio" name="format" value="hexadecimal" onclick="saveFormat(this.value)">
                <label for="hexadecimal">Hexadecimal</label>

                <br />

                <div style="margin: 0 0 107px 0">
                    <input type="tel" name="first" class="form-control input-control" pattern='^[0-1]+$'
                           placeholder=" First number" autocomplete="off" required>

                    <select id="operation" name="operation" class="selectpicker">
                        <option selected>+</option>
                        <option>-</option>
                    </select>

                    <input type="tel" name="second" class="form-control input-control" pattern='^[0-1]+$'
                           placeholder=" Second number" autocomplete="off" required>
                </div>

                <input type="button" id="calcBtn" value="Calculation" onclick="calc()"
                       class="form-control input-control">
            </div>
        </form>
        <input type="tel" name="result" class="form-control input-control"
               placeholder=" Result" autocomplete="off">
    </div>
</body>
</html>
