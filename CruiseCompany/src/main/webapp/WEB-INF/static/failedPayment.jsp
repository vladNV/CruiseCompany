<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="content container">
        <h4><fmt:message bundle="${msg}" key="payment.fail"/></h4>
        <hr>
        <h5><fmt:message bundle="${msg}" key="${requestScope.cause}"/> </h5>
        <hr>
        <a href="${pageContext.request.contextPath}/back">
            <fmt:message bundle="${msg}" key="back"/>
        </a>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
