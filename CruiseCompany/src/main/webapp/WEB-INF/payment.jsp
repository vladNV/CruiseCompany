<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title><fmt:message bundle="${msg}" key="to.pay"/></title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="wrapper">
        <div class="content">
            <div class="col-sm-12">
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                    <h2><fmt:message bundle="${msg}" key="order"/></h2>
                </div>
                <div class="col-sm-6"></div>
            </div>
            <div class="col-sm-12">
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                    <b><fmt:message bundle="${msg}" key="cruise_name"/>: </b>
                    ${sessionScope.tour.name} <br>
                    <b><fmt:message bundle="${msg}" key="region"/>: </b>
                    ${sessionScope.tour.region}<br>
                    <b><fmt:message bundle="${msg}" key="arrival"/>: </b>
                    ${sessionScope.tour.arrival} <br>
                    <b><fmt:message bundle="${msg}" key="departure"/>: </b>
                    ${sessionScope.tour.departure} <br>
                    <b><fmt:message bundle="${msg}" key="duration"/>: </b>
                    ${sessionScope.tour.duration.days} <fmt:message bundle="${msg}" key="days"/>
                    ${sessionScope.tour.duration.hours} <fmt:message bundle="${msg}" key="hours"/>
                    ${sessionScope.tour.duration.minutes} <fmt:message bundle="${msg}" key="minutes"/>
                    <br>
                    <b><fmt:message bundle="${msg}" key="ticket_type"/>: </b>
                    ${sessionScope.cart.ticket.type} <br>
                    <b><fmt:message bundle="${msg}" key="amount"/>: </b>
                    ${sessionScope.cart.ticket.amountPassengers} <br>
                    <div class="ticket-table">
                        <c:forEach items="${sessionScope.cart.excursions}" var="excursion">
                            <a class="ticket-row" href="#">
                                <div class="ticket-cell">${excursion.name}</div>
                                <div class="ticket-cell">${excursion.port.name}</div>
                                <div class="ticket-cell">${excursion.port.country}</div>
                                <div class="ticket-cell">${excursion.price}</div>
                                <div class="ticket-cell">
                                    <form method="post" action="${pageContext.request.contextPath}/ticket/add_excursion/${excursion.id}">
                                        <button class="btn btn-success">
                                            <fmt:message bundle="${msg}" key="add"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="ticket-cell">
                                    <form method="post" action="${pageContext.request.contextPath}/ticket/remove_excursion/${excursion.id}">
                                        <button class="btn btn-danger">
                                            <fmt:message bundle="${msg}" key="remove"/>
                                        </button>
                                    </form>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                    <h4><span style="color: red"><b>
                        <fmt:message bundle="${msg}" key="to.pay"/>
                    </b> - ${requestScope.price}</span></h4>
                </div>
                <div class="col-sm-5" style="border: 1px solid gainsboro">
                    <c:forEach items="${sessionScope.tour.routes}" var="route">
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
                <div class="col-sm-1"></div>
            </div>
            <div class="col-sm-12">
                <hr />
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                    <form action="payment" method="post">
                        <div class="form-group">
                            <label for="card"> <fmt:message bundle="${msg}" key="card_number"/> VIZA\Specialistcard
                                <span style="color:red">*</span>
                                <input required class="form-control" id="card" name="card"/>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV2\CVC <span style="color:red">*</span>
                                <input required class="form-control" id="cvv" name="cvv" type="password">
                            </label>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-lg btn-success">
                                <fmt:message bundle="${msg}" key="buy"/>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6"></div>
            </div>
        </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
    </div>
</body>
</html>
