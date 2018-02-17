<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
</head>
<body>
<div>
    <form method="GET" action="${pageContext.request.contextPath}/doRegistration">

        <table>
            <tr>
                <td>Email:</td>
                <td><input name="mail"/></td>
            </tr>
            <tr>
                <td>First Name *</td>
                <td><input name="fname"/></td>
            </tr>
            <tr>
                <td>Last Name *</td>
                <td><input name="lname"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Registrate"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
