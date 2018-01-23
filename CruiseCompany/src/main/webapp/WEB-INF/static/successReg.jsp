<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="success.reg"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="container">
        <fmt:message bundle="${msg}" key="registration_success"/>
        <a href="/login"><fmt:message bundle="${msg}" key="sign_in"/></a>
    </div>
    <hr/>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>