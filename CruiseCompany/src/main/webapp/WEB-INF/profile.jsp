<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="profile"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="content container">
        <div class="col-sm-12">
            <div class="col-sm-3">
                <b>Login:</b> ${sessionScope.user.login} <br>
                <b>Email:</b> ${sessionScope.user.email} <br>
            </div>
            <div class="col-sm-9">
                <h2><fmt:message bundle="${msg}" key="ticket.active"/> </h2>
                <div class="ticket-table">
                    <div class="ticket-header">
                        <div class="ticket-cell">#</div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="person"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="place"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="ticket_type"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="cruise_name"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="arrival"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="passenger.amount"/></div>
                    </div>
                    <c:forEach items="${requestScope.activeTickets}" var="a">
                        <div class="ticket-row">
                            <div class="ticket-cell">
                                    ${a.id}
                            </div>
                            <div class="ticket-cell">
                                    ${a.person}
                            </div>
                            <div class="ticket-cell">
                                    ${a.place}
                            </div>
                            <div class="ticket-cell">
                                    ${a.type}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.name}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.departure}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.arrival}
                            </div>
                            <div class="ticket-cell">
                                    ${a.amountPassengers}
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <h2><fmt:message bundle="${msg}" key="ticket.old"/></h2>
                <div class="ticket-table">
                    <div class="ticket-header">
                        <div class="ticket-cell">#</div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="person"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="place"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="ticket_type"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="cruise_name"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="arrival"/></div>
                    </div>
                    <c:forEach items="${requestScope.oldTickets}" var="a">
                        <div class="ticket-row">
                            <div class="ticket-cell">
                                    ${a.id}
                            </div>
                            <div class="ticket-cell">
                                    ${a.person}
                            </div>
                            <div class="ticket-cell">
                                    ${a.place}
                            </div>
                            <div class="ticket-cell">
                                    ${a.type}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.name}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.departure}
                            </div>
                            <div class="ticket-cell">
                                    ${a.tour.arrival}
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
