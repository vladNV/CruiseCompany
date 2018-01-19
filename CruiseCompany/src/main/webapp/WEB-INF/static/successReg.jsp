<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 18.01.2018
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ page isELIgnored="false" %>
<fmt:bundle basename="messages">
<html>
<head>
    <title>Successful registration</title>
</head>
<body>
    <div class="container">
        <fmt:message key="registration_success"/>
        <a href="/login"><fmt:message key="sign_in"/></a>
    </div>
    <hr/>
</body>
</html>
</fmt:bundle>