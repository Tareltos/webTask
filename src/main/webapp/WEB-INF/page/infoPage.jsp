<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename=".content"/>
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
<jsp:include page="_header.jsp"/>
<table>
    <br>
    <br>
    <ul class="hr">
        <li><fmt:message key="label.Email"/>: ${mail}</li>
        <li><fmt:message key="label.user.fname"/>: ${fname}</li>
        <li><fmt:message key="label.user.lname"/>: ${lname}</li>
    </ul>
    <br>
    <br>
    <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/loadFile">

        <fmt:message key="loadingfile.fild" var="buttonValue"/>
        <input name="data" type="file">

        <br>
        <fmt:message key="loadingfile.button" var="buttonValue"/>
        <input type="submit" name="button" value="${buttonValue}">
    </form>


</table>
</body>
</html>
