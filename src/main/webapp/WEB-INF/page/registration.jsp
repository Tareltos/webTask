<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename=".content"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/doRegistration">

        <table>
            <tr>
                <td><label for="mail"><fmt:message key="label.Email"/>:</label></td>
                <td><input id="mail" name="mail"></td>
            </tr>

            <tr>
                <td><label for="fname"><fmt:message key="label.user.fname"/>:</label></td>
                <td><input id="fname" name="fname"/></td>
            </tr>
            <tr>
                <td><label for="lname"><fmt:message key="label.user.lname"/>:</label></td>
                <td><input id="lname" name="lname"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <fmt:message key="login.button.registration" var="buttonValue"/>
                <td><input type="submit" value="${buttonValue}"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>