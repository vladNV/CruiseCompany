<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@include file="../taglib.jsp"%>
<html>
<head>
    <title>500</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
    <div class="content container">
        <h1>500</h1>
        <fmt:message bundle="${msg}" key="error500"/>
        <hr>
        <a href="${pageContext.request.contextPath}/back">
            <fmt:message bundle="${msg}" key="back"/>
        </a>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>