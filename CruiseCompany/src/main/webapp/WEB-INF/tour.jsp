<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <title>Tour</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="col-sm-12">
        <h2>${requestScope.tour.name}</h2>
    </div>
    <div class="col-sm-12"style="margin-top: 100px;">
        <div class="col-sm-4">Tour map</div>
        <div class="col-sm-4">Tour description</div>
        <div class="col-sm-4">Ship image</div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div class="route-table">
                <div class="route-header">
                    <div class="route-cell"><fmt:message key="route"/></div>
                    <div class="route-cell"><fmt:message key="deparute"/></div>
                    <div class="route-cell"><fmt:message key="arrival"/></div>
                    <div class="route-cell"><fmt:message key="port"/></div>
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
                    <div class="ticket-cell"><fmt:message key="amount"/></div>
                    <div class="ticket-cell"><fmt:message key="deparute"/></div>
                    <div class="ticket-cell"><fmt:message key="arrival"/></div>
                    <div class="ticket-cell"><fmt:message key="ticket_type"/></div>
                    <div class="ticket-cell"><fmt:message key="price"/></div>
                </div>
                <c:forEach items="${requestScope.tour_tickets}" var="a">
                    <div class="ticket-row">
                        <div class="ticket-cell">
                            ${a.agg}
                        </div>
                        <div class="ticket-cell">
                            ${a.entity.departure}
                        </div>
                        <div class="ticket-cell">
                            ${a.entity.arrival}
                        </div>
                        <div class="ticket-cell">
                            ${a.entity.type}
                        </div>
                        <div class="ticket-cell">
                            ${a.entity.price}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
</fmt:bundle>