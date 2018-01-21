<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Profile</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="content container">
        <div class="col-sm-12">
            <div class="col-sm-5">
                <b>Login:</b> ${sessionScope.user.login} <br>
                <b>Email:</b> ${sessionScope.user.email} <br>
                <b>Country: </b><br>
                <hr>
                <h2>Your history</h2>
            </div>
            <div class="col-sm-7">
                <h2>Active ticket</h2>
                <div class="ticket-table">
                    <div class="ticket-header">
                        <div class="ticket-cell">#</div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="arrival"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="ticket_type"/></div></div>
                    <c:forEach items="${requestScope.activeTickets}" var="a">
                        <div class="ticket-row">
                            <div class="ticket-cell">
                                    ${a.id}
                            </div>
                            <div class="ticket-cell">
                                    ${a.departure}
                            </div>
                            <div class="ticket-cell">
                                    ${a.arrival}
                            </div>
                            <div class="ticket-cell">
                                    ${a.type}
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <h2>Old ticket</h2>
                <div class="ticket-table">
                    <div class="ticket-header">
                        <div class="ticket-cell">#</div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="arrival"/></div>
                        <div class="ticket-cell"><fmt:message bundle="${msg}" key="ticket_type"/></div>
                    </div>
                    <c:forEach items="${requestScope.oldTickets}" var="a">
                        <div class="ticket-row">
                            <div class="ticket-cell">
                                    ${a.id}
                            </div>
                            <div class="ticket-cell">
                                    ${a.departure}
                            </div>
                            <div class="ticket-cell">
                                    ${a.arrival}
                            </div>
                            <div class="ticket-cell">
                                    ${a.type}
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
