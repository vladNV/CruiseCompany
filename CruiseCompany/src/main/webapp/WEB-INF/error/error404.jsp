<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<%@include file="../taglib.jsp"%>
<html>
<head>
    <title>404</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
    <div class="content container">
        <h1>404</h1>
        <fmt:message bundle="${msg}" key="error404"/>
        <hr>
        <a href="${pageContext.request.contextPath}/back">
            <fmt:message bundle="${msg}" key="back"/>
        </a>
    </div>
    <%--<c:import url="/WEB-INF/error/errorPage.jsp"/>--%>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
