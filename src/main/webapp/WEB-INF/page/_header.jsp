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
<div class="header-container">
    <div class="header-Locale">
        <form>
            <fmt:message key="language.text.label"/>
                <select id="language" name="language" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>
                </select><span style='padding-left:550px;'><fmt:message key="label.app.name"/></span>
        </form>
    </div>
</div>

