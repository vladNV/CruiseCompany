<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <title>Ticket</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="col-sm-12">
    <div class="col-sm-2"></div>
    <div class="info-page col-sm-8">
        <div>
            <h3><fmt:message key="ticket_type"/></h3> ${sessionScope.ticket.type}
        </div>
        <div>
            <h3><fmt:message key="deparute"/></h3> ${sessionScope.ticket.departure}
        </div>
        <div>
            <h3><fmt:message key="arrival"/></h3> ${sessionScope.ticket.arrival}
        </div>
        <div>
            <h3><fmt:message key="price"/></h3> ${sessionScope.ticket.price}
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
<div class="col-sm-12">
    <hr />
    <div class="col-sm-2"></div>
    <div class="excursion-page col-sm-8">
        <div>
            <h4><fmt:message key="short_desc_excurs"/></h4>
        </div>
        <div class="ticket-table">
            <div class="ticket-header">
                <div class="ticket-cell"><fmt:message key="excursion"/></div>
                <div class="ticket-cell"><fmt:message key="port"/></div>
                <div class="ticket-cell"><fmt:message key="country"/></div>
                <div class="ticket-cell"><fmt:message key="price"/></div>
            </div>
            <c:forEach items="${requestScope.excursions}" var="excursion">
            <a class="ticket-row" href="#">
                <div class="ticket-cell">${excursion.name}</div>
                <div class="ticket-cell">${excursion.port.name}</div>
                <div class="ticket-cell">${excursion.port.country}</div>
                <div class="ticket-cell">${excursion.price}</div>
            </a>
            </c:forEach>
        </div>
    </div>
    <hr />
</div>
<div class="col-sm-12">
    <div class="col-sm-2"></div>
    <div class="form-page col-sm-8">
            <form action="buy" method="post">
                <div class="form-group">
                    <label for="card">
                        <fmt:message key="card_number"/>
                    </label>
                    <input id="card" name="card" class="form-control"/>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary"><fmt:message key="buy"/></button>
                </div>
            </form>
    </div>
    <div class="col-sm-2"></div>
</div>
</body>
</html>
</fmt:bundle>