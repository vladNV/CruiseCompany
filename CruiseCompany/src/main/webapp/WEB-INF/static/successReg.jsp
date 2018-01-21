<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Successful registration</title>
</head>
<body>
    <div class="container">
        <fmt:message key="registration_success"/>
        <a href="/login"><fmt:message bundle="${msg}" key="sign_in"/></a>
    </div>
    <hr/>
</body>
</html>