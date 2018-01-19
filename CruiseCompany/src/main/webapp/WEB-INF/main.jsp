<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title>Title</title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <c:if test="${sessionScope.user != null}">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:if>
    <div class="col-sm-12 center-block">
        <h2>Tours</h2>
        <div class="ticket-table">
            <c:forEach items="${requestScope.tours}" var="tour">
                <a href="${pageContext.request.contextPath}/tour/${tour.id}" class="ticket-row">
                    <div class="ticket-cell">${tour.name}</div>
                    <div class="ticket-cell">${tour.departure}</div>
                    <div class="ticket-cell">${tour.region}</div>
                    <div class="ticket-cell">${tour.duration}</div>
                    <div class="ticket-cell">${tour.ship.name}</div>
                </a>
            </c:forEach>
        </div>
    </div>
</body>
</html>
</fmt:bundle>