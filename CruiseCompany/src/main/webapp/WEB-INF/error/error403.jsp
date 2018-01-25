<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isErrorPage="true" %>
<%@include file="../taglib.jsp"%>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="title.403"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<c:set var="lastPath" value="${sessionScope.path}" scope="session"/>
<div class="wrapper">
    <div class="content container">
        <fmt:message bundle="${msg}" key="error403"/>
        <hr>
        <c:choose>
            <c:when test="${sessionScope.role == 'GUEST'}">
                <a href="${pageContext.request.contextPath}/back">
                    <fmt:message bundle="${msg}" key="back"/>
                </a>
            </c:when>
            <c:otherwise>
                <c:redirect url="${pageContext.request.contextPath}/main"/>
            </c:otherwise>
        </c:choose>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
