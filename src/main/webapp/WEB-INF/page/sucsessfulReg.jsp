<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename=".content"/>
<!DOCTYPE html>
<html lang="${language}">
<html>
<head>
    <title>Information</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<p>${name}<fmt:message key="reg.message.text"/>${mail}
</p>
</body>
</html>
