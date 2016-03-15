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
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">

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
                var clientName = $('#clientName').val();
                var petType = $('#petType').val();
                var petName = $('#petName').val();
                var petSex = $('#petSex').val();
                var petAge = $('#petAge').val();
                $.ajax({
                    type: 'POST',
                    data: {
                        nameClient : clientName,
                        petType : petType,
                        petName : petName,
                        petSex : petSex,
                        petAge : petAge
                    },
                    url: 'client/index',
                    success: function(result) {
//                        $('#createClient').html(result);
                        loadClients();
                    }
                });
            });
        });
        function loadClients() {
            $.ajax({
                method: 'GET',
                success: function(result) {
                    <c:if test="${!clients.isEmpty()}">
                    var table = '<hr /> ' +
                                '<div class="body">' +
                                '<table border="2" class="table">';
                    table +=        "<caption>База данных клиентов</caption>" +
                                    "<tbody> " +
                                        "<tr> " +
                                           "<th>Имя клиента</th> " +
                                           "<th>Тип питомца</th> " +
                                           "<th>Имя питомца</th> " +
                                           "<th>Пол питомца</th> " +
                                           "<th>Возраст питомца</th> " +
                                        "</tr>";
                    <c:forEach items="${clients}" var="client" varStatus="status">
                    table +=            "<tr>" +
                                            "<td>" + ${client.name} + "</td>" +
                                            "<td>" + ${client.pet.petType} + "</td>" +
                                            "<td>" + ${client.pet.name} + "</td>" +
                                            "<td>" + ${client.pet.petSex} + "</td>" +
                                            "<td>" + ${client.pet.age} + "</td>" +
                                        "</tr>";
                    </c:forEach>
                    table +=        "</tbody>" +
                                 "</table>" +
                                 "</div> ";
                    $('.createClient').html(table);
                    </c:if>
                }
            });
        }
//        function loadUsers() {
//            $.ajax('client/index', {
//                method : 'get',
//                success: function(data) {
//                    var table;
//                    var size = data.length;
//                    for (var i = 0; i != size; i++) {
//                        table = "<tr>" +
//                                    "<td>" + data[i].clientName + "</td>" +
//                                    "<td>" + data[i].petType + "</td>" +
//                                    "<td>" + data[i].petName + "</td>" +
//                                    "<td>" + data[i].petSex + "</td>" +
//                                    "<td>" + data[i].petAge + "</td>" +
//                                "</tr>"
//                    }
//                    $('#createClient').html(table);
//                }
//            });
//        }
//        function createClient() {
//            if (validate()) {
//                $.ajax('client/index', {
//                    method : 'post',
//                    data: JSON.stringify({
//                        clientName : $('#clientName').val(),
//                        petType : $('#petType').val(),
//                        petName : $('#petName').val(),
//                        petSex : $('#petSex').val(),
//                        petAge : $('#petAge').val()}),
//                    complete: function(data) {
//                        loadUsers();
//                    }
//                });
//            }
//        }
//
//        function validate() {
//            var result = true;
//            if ($('#clientName').val() == '' || $('#petName').val() == '') {
//                if ($('#clientName').val() == '') {
//                    $('#petName').css('box-shadow', 'rgba(102, 175, 233, 0) 0px 0px 25px inset');
//
//                    $('#clientName').css('box-shadow', 'rgba(102, 175, 233, 1) 0px 0px 25px inset');
//                }
//                else {
//                    $('#clientName').css('box-shadow', 'rgba(102, 175, 233, 0) 0px 0px 25px inset');
//
//                    $('#petName').css('box-shadow', 'rgba(102, 175, 233, 1) 0px 0px 25px inset');
//                }
//                result = false;
//            }
//            return result;
//        }
//
//        function loadUsers() {
//            $.ajax('client/index', {
//                method : 'get',
//                success: function(data) {
//                    var table;
//                    var size = data.length;
//                    for (var i = 0; i != size; i++) {
//                        table = "<tr>" +
//                                    "<td>" + data[i].clientName + "</td>" +
//                                    "<td>" + data[i].petType + "</td>" +
//                                    "<td>" + data[i].petName + "</td>" +
//                                    "<td>" + data[i].petSex + "</td>" +
//                                    "<td>" + data[i].petAge + "</td>" +
//                                 "</tr>"
//                    }
//                    $('#createClient').html(table);
//                }
//            });
//        }
    </script>
</head>
<body>
    <div class="container">
        <header>
            <div>
                <h1>Клиника домашних <br>животных</h1>
                <p class="lead">Внесите требуемые данные:</p>
            </div>
            <form action="" method="post">
                <div class="form-group">
                    <input type="text" id="clientName" class="form-control input-control" placeholder="Имя клиента" required>
                    <br>
                    <input type="text" id="petType" class="form-control input-control" placeholder="Тип питомца">
                    <input type="text" id="petName" class="form-control input-control" placeholder="Имя питомца" required>
                    <%--<select id="petSex" class="form-control input-control">--%>
                        <%--<option selected="selected" disabled>Пол питомца</option>--%>
                        <%--<option value="male">Мужской</option>--%>
                        <%--<option value="female">Женский</option>--%>
                    <%--</select>--%>
                    <%--<input type="number" id="petAge" class="form-control input-control" min="0" max="300" placeholder="Возраст питомца">--%>
                    <input type="text" id="petSex" class="form-control input-control" placeholder="Пол питомца">
                    <input type="text" id="petAge" class="form-control input-control" placeholder="Возраст питомца">
                    <input type="button" id="button" class="btn btn-primary input-control" value="Создать">
                </div>
            </form>
        </header>
        <div class="createClient"></div>
        <%--<hr />--%>
        <%--<div class="body">--%>
            <!-- <h1>Список пуст!</h1> -->
            <%--<table border="2" class="table">--%>
                <%--<caption>База данных клиентов</caption>--%>
                <%--<tbody>--%>
                <%--<tr>--%>
                    <%--<th>Имя клиента</th>--%>
                    <%--<th>Тип питомца</th>--%>
                    <%--<th>Имя питомца</th>--%>
                    <%--<th>Пол питомца</th>--%>
                    <%--<th>Возраст питомца</th>--%>
                <%--</tr>--%>
                <%--<div id="createClient"></div>--%>
                <%--<c:if test="${clients.isEmpty()}">--%>
                    <%--<tr>--%>
                        <%--<td colspan="5" >Список пуст!</td>--%>
                    <%--</tr>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!clients.isEmpty()}">--%>
                    <%--<div class="createClient">--%>
                        <%--<c:forEach items="${clients}" var="client" varStatus="status">--%>
                            <%--<tr valign="top">--%>
                                <%--<td style="padding: 10px;">--%>
                                    <%--${client.name},--%>
                                    <%--${client.pet.petType},--%>
                                    <%--${client.pet.name},--%>
                                    <%--${client.pet.petSex},--%>
                                    <%--${client.pet.age}--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
    </div>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
