<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head><title>MainPage</title></head>
<body>
<h5>
    <form method="POST" action="${pageContext.request.contextPath}/doAuth">
        <table>
            <tr>
                <td>Email:</td>
                <td><input name="mail"/></td>
            </tr>
            <tr>
                <td>Password *</td>
                <td><input name="password"/></td>
            </tr>
            <tr>
                <td>

                    <input type="submit" name="button" value="Sign In"/>

                </td>
            </tr>
        </table>
    </form>

    <form action="${pageContext.request.contextPath}/registrationForm" method="POST">
        <input type="submit" name="button" value="Register"/>
    </form>
</h5>
</body>
</html>
