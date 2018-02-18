<%--
  Created by IntelliJ IDEA.
  User: Vitali
  Date: 16.02.2018
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    ul.hr {
        margin: 0; /* Обнуляем значение отступов */
        padding: 4px; /* Значение полей */
    }

    ul.hr li {
        display: inline; /* Отображать как строчный элемент */
        margin-right: 5px; /* Отступ слева */
        border: 1px solid #000; /* Рамка вокруг текста */
        padding: 3px; /* Поля вокруг текста */
    }
</style>
<head>
    <title>UserInfo</title>
</head>
<body>

<table>
    <br>
    <br>
    <ul class="hr">
        <li>Email: ${mail}</li>
        <li>First Name: ${fname}</li>
        <li>Last Name: ${lname}</li>
    </ul>
    <br>
    <br>
    <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/loadFile">
        <input name="data" type="file">
        <br>
        <input type="submit" name="button" value="Send">
    </form>
</table>
</body>
</html>
