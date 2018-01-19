<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 19.01.2018
  Time: 1:41 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="col-sm-12">
        <div class="col-sm-4">Tour map</div>
        <div class="col-sm-4">Tour description</div>
        <div class="col-sm-4">Ship image</div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-6">
            <div class="route-table">
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
                <c:forEach items="${requestScope.tour.tickets}" var="ticket">
                    <div class="ticket-row">
                        <div class="ticket-cell">
                            ${ticket.deparute}
                        </div>
                        <div class="ticket-cell">
                            ${ticket.arrival}
                        </div>
                        <div class="ticket-cell">
                            ${ticket.type}
                        </div>
                        <div class="ticket-cell">
                            ${ticket.price}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
</fmt:bundle>