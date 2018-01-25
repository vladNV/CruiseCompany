<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
   <title><fmt:message bundle="${msg}" key="ticket"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="content container">
        <h4><fmt:message bundle="${msg}" key="payment.success"/> </h4>
        <hr>
        <a onclick="window.history.back();">
            <fmt:message bundle="${msg}" key="back"/>
        </a>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
