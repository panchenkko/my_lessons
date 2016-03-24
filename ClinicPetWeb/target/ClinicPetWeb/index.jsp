<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Клиника домашних животных</title>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/indexStyle.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="js/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.12.1.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#button').click(function() {
                if ($('#clientName').val() == '' || $('#petName').val() == '') {
                    if ($('#clientName').val() == '') {
                        $('#petName').css('box-shadow', 'rgba(102, 175, 233, 0) 0px 0px 25px inset');
                        $('#clientName').css('box-shadow', 'rgba(102, 175, 233, 1) 0px 0px 25px inset');
                    }
                    else {
                        $('#clientName').css('box-shadow', 'rgba(102, 175, 233, 0) 0px 0px 25px inset');
                        $('#petName').css('box-shadow', 'rgba(102, 175, 233, 1) 0px 0px 25px inset');
                    }
                }
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <header>
            <div>
                <h1>Клиника домашних <br>животных</h1>
                <p class="lead">Внесите требуемые данные:</p>
            </div>
            <form action="${pageContext.servletContext.contextPath}/client/index" method="POST">
                <div class="form-group">
                    <input type="text" name="clientName" id="clientName" class="form-control input-control" placeholder="Имя клиента" required>
                    <br>
                    <input type="text" name="petType" class="form-control input-control" placeholder="Тип питомца">
                    <input type="text" name="petName" id="petName" class="form-control input-control" placeholder="Имя питомца" required>
                    <select name="petSex" class="form-control input-control">
                        <option selected="selected" disabled>Пол питомца</option>
                        <option value="Мужской">Мужской</option>
                        <option value="Женский">Женский</option>
                    </select>
                    <input type="number" name="petAge" class="form-control input-control" min="0" max="300" placeholder="Возраст питомца">
                    <input type="submit" id="button" class="btn btn-primary input-control" value="Создать">
                </div>
            </form>
        </header>
        <c:if test="${!clients.isEmpty()}">
        <hr />
        <div class="body">
            <a href="${pageContext.servletContext.contextPath}/client/search" id="searchBtn">
                <button class="btn btn-primary">Поиск</button>
            </a>
            <table border="2" class="table">
                <caption>База данных клиентов</caption>
                <tbody>
                <tr>
                    <th>Имя клиента</th>
                    <th>Тип питомца</th>
                    <th>Имя питомца</th>
                    <th>Пол питомца</th>
                    <th id="Age">Возраст питомца</th>
                    <th>Доп. действия</th>
                </tr>
                <div class="createClient">
                    <c:forEach items="${clients}" var="client" varStatus="status">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.pet.petType}</td>
                            <td>${client.pet.name}</td>
                            <td>${client.pet.petSex}</td>
                            <c:if test="${client.pet.age == 0 || client.pet.age == ''}">
                                <td> - </td>
                            </c:if>
                            <c:if test="${client.pet.age == 1 || client.pet.age == 21}">
                                <td>${client.pet.age} год</td>
                            </c:if>
                            <c:if test="${client.pet.age > 1 && client.pet.age < 5 ||
                                          client.pet.age > 21 && client.pet.age < 25}">
                                <td>${client.pet.age} года</td>
                            </c:if>
                            <c:if test="${client.pet.age >= 5 && client.pet.age <= 20 || client.pet.age >= 25}">
                                <td>${client.pet.age} лет</td>
                            </c:if>
                            <td id="linkAction">
                                <a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">Редактировать</a>
                                <a href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}">Удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                </div>
                </tbody>
            </table>
        </div>
        </c:if>
    </div>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
