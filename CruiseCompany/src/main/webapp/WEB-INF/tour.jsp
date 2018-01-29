<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <title>${requestScope.tour.name}</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<c:set scope="request" value="${requestScope.tour.id}" var="tourId"/>
<c:set scope="request" value="${requestScope.tour.tickets}" var="tickets"/>
<div class="wrapper">
<div class="content">
    <div class="col-sm-12">
        <h2>${requestScope.tour.name}</h2>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div class="route-table">
                <div class="route-header">
                    <div class="route-cell"><fmt:message bundle="${msg}" key="route"/></div>
                    <div class="route-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                    <div class="route-cell"><fmt:message bundle="${msg}" key="arrival"/></div>
                    <div class="route-cell"><fmt:message bundle="${msg}" key="port"/></div>
                </div>
                <c:forEach items="${requestScope.tour.routes}" var="route">
                    <div class="route-row">
                        <div class="route-cell">
                            ${route.name}
                        </div>
                        <div class="route-cell">
                            ${route.departure}
                        </div>
                        <div class="route-cell">
                            ${route.arrival}
                        </div>
                        <div class="route-cell">
                            ${route.port.name}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ticket-table">
                <div class="ticket-header">
                    <div class="ticket-cell"><fmt:message bundle="${msg}" key="ticket_type"/></div>
                    <div class="ticket-cell"><fmt:message bundle="${msg}" key="price.person"/></div>
                    <div class="ticket-cell"><fmt:message bundle="${msg}" key="place"/> </div>
                </div>
                <c:forEach items="${requestScope.tickets}" var="ticket">
                    <a class="ticket-row" href="${pageContext.request.contextPath}/ticket/${ticket.id}">
                        <div class="ticket-cell">
                            ${ticket.type}
                        </div>
                        <div class="ticket-cell">
                            <fmt:formatNumber value="${currency * ticket.price / 1000}" type="currency"/>
                        </div>
                        <div class="ticket-cell">
                            ${ticket.place}
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>