<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title><fmt:message key="tours"/></title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="col-sm-12 center-block">
        <h2>Tours</h2>
        <div class="tour-table">
            <div class="tour-header">
                <div class="tour-cell"><fmt:message key="cruise_name"/></div>
                <div class="tour-cell"><fmt:message key="deparute"/></div>
                <div class="tour-cell"><fmt:message key="region"/></div>
                <div class="tour-cell"><fmt:message key="duration"/></div>
                <div class="tour-cell"><fmt:message key="ship"/></div>
            </div>
            <c:forEach items="${requestScope.tours}" var="tour">
                <a href="${pageContext.request.contextPath}/tour/${tour.id}" class="tour-row">
                    <div class="tour-cell">${tour.name}</div>
                    <div class="tour-cell">${tour.departure}</div>
                    <div class="tour-cell">${tour.region}</div>
                    <div class="tour-cell">${tour.duration}</div>
                    <div class="tour-cell">${tour.ship.name}</div>
                </a>
            </c:forEach>
        </div>
    </div>
</body>
</html>
</fmt:bundle>