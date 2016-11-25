<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Scientific Calculator</title>

    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/css/indexStyle.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="js/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.1.min.js"></script>

    <script type="text/javascript">
        function transfer(input) {
            $.ajax({
                url: '/transfer',
                type: 'get',
                data: ({number: input.value, name: input.name}),
                success: function(data) {
                    console.log(data);

                    $("input[name='binary']").val(data.binary);
                    $("input[name='octal']").val(data.octal);
                    $("input[name='decimal']").val(data.decimal);
                    $("input[name='hexadecimal']").val(data.hexadecimal);
                }
            });
        }
    </script>
</head>
<body>
	<div class="container">

        <a href="${pageContext.servletContext.contextPath}/pageCalc" id="calcBtn">
            <button class="form-control input-control">Calc</button>
        </a>

        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <div class="form-group">
                <span>Bin: </span><input type="tel" name="binary" onkeyup="transfer(binary)" pattern="^[0-1]+$"
                       class="form-control input-control transfer" placeholder=" Input binary number"
                       autocomplete="off" required>

                <span>Dec: </span><input type="tel" name="decimal" onkeyup="transfer(decimal)" pattern="^[0-9]+$"
                       class="form-control input-control transfer" placeholder=" Input decimal number"
                       autocomplete="off" required>

                <span>Oct: </span><input type="tel" name="octal" onkeyup="transfer(octal)" pattern="^[0-7]+$"
                       class="form-control input-control transfer" placeholder=" Input octal number"
                       autocomplete="off" required>

                <span>Hex: </span><input type="tel" name="hexadecimal" onkeyup="transfer(hexadecimal)" pattern="^[a-fA-F\d]+$"
                       class="form-control input-control transfer" placeholder=" Input hexadecimal number"
                       autocomplete="off" required>

                <input type="submit" id="button" value="Save" class="form-control input-control">
            </div>
        </form>
    </div>
    <c:if test="${!numbers.isEmpty()}">
        <hr />
        <div class="body">
            <table border="2" class="table">
                <caption>Save Actions</caption>
                <tbody>
                <tr>
                    <th id="date">Date and time</th>
                    <th id="binary">Binary number</th>
                    <th id="octal">Octal number</th>
                    <th id="decimal">Decimal number</th>
                    <th id="hexadecimal">Hexadecimal number</th>
                </tr>
                <div class="createClient">
                    <c:forEach items="${numbers}" var="number" varStatus="status">
                        <tr>
                            <td>${number.getDate()}</td>
                            <td>${number.getBinary()}</td>
                            <td>${number.getOctal()}</td>
                            <td>${number.getDecimal()}</td>
                            <td>${number.getHexadecimal()}</td>
                        </tr>
                    </c:forEach>
                </div>
                </tbody>
            </table>
        </div>
    </c:if>
</body>
</html>