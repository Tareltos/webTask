<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename=".content"/>
<!DOCTYPE html>
<html lang="${language}">
<html>
<head><title>MainPage</title></head>
<body>

<jsp:include page="WEB-INF/page/_header.jsp"/>

<table style="margin:auto">
    <form method="POST" action="${pageContext.request.contextPath}/doAuth">
        <table>
            <tr>
                <td><label for="mail"><fmt:message key="label.Email"/>:</label></td>
                <td><input id="mail" name="mail"></td>
            </tr>
            <tr>
                <td><label for="pass"><fmt:message key="label.password"/>:</label></td>
                <td><input id="pass" name="password"></td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="login.button.submit" var="buttonValue"/>
                    <input type="submit" name="button" value="${buttonValue}"/>
                </td>
            </tr>
        </table>
    </form>
    <br>
    <form action="${pageContext.request.contextPath}/registrationForm" method="POST">
        <fmt:message key="login.button.registration" var="buttonValue"/>
        <input type="submit" name="button" value="${buttonValue}"/>
    </form>
    <br>
</table>
</h5>
</body>
</html>
