<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title><fmt:message bundle="${msg}" key="tours"/></title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="wrapper">
    <div class="col-sm-12 center-block content">
        <h2>Tours</h2>
        <div class="tour-table">
            <div class="tour-header">
                <div class="tour-cell"><fmt:message bundle="${msg}" key="cruise_name"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="region"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="duration"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="ship"/></div>
            </div>
            <c:forEach items="${requestScope.tours}" var="tour">
                <a href="${pageContext.request.contextPath}/tour/${tour.id}" class="tour-row">
                    <div class="tour-cell">${tour.name}</div>
                    <div class="tour-cell">
                    <jt:format value="${tour.departure}" style="FS"/>
                    </div>
                    <div class="tour-cell">${tour.region}</div>
                    <div class="tour-cell">
                            ${tour.duration.days} <fmt:message bundle="${msg}" key="days"/>
                            ${tour.duration.hours} <fmt:message bundle="${msg}" key="hours"/>
                            ${tour.duration.minutes} <fmt:message bundle="${msg}" key="minutes"/>
                    </div>
                    <div class="tour-cell">${tour.ship.name}</div>
                </a>
            </c:forEach>
        </div>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
    </div>
</body>
</html>