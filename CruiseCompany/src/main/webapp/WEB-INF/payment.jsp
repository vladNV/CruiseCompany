<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title><fmt:message bundle="${msg}" key="to.pay"/></title>
</head>
<body>
<c:set var="ticket" value="${sessionScope.cart.ticket}" scope="session"/>
<c:set var="excursions" value="${sessionScope.cart.excursions}" scope="session"/>
    <div class="wrapper">
        <c:import url="/WEB-INF/static/menu.jsp"/>
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
                    ${sessionScope.ticket.tour.name} <br>
                    <b><fmt:message bundle="${msg}" key="region"/>: </b>
                    ${sessionScope.ticket.tour.region}<br>
                    <b><fmt:message bundle="${msg}" key="arrival"/>: </b>
                    ${sessionScope.ticket.tour.arrival} <br>
                    <b><fmt:message bundle="${msg}" key="departure"/>: </b>
                    ${sessionScope.ticket.tour.departure} <br>
                    <b><fmt:message bundle="${msg}" key="duration"/>: </b>
                    ${sessionScope.ticket.tour.duration.days}
                    <fmt:message bundle="${msg}" key="days"/>
                    ${sessionScope.ticket.tour.duration.hours}
                    <fmt:message bundle="${msg}" key="hours"/>
                    ${sessionScope.ticket.tour.duration.minutes}
                    <fmt:message bundle="${msg}" key="minutes"/>
                    <br>
                    <b><fmt:message bundle="${msg}" key="ticket_type"/>: </b>
                    ${sessionScope.ticket.type} <br>
                    <b><fmt:message bundle="${msg}" key="amount"/>: </b>
                    ${sessionScope.ticket.amountPassengers} <br>
                    <div class="ticket-table">
                        <c:forEach items="${sessionScope.excursions}" var="e">
                            <div class="ticket-row">
                                <div class="ticket-cell">${e.name}</div>
                                <div class="ticket-cell">${e.port.name}</div>
                                <div class="ticket-cell">${e.port.country}</div>
                                <div class="ticket-cell">
                                    <fmt:formatNumber value="${currency * e.price / 1000}"
                                                                              type="currency"/></div>
                            </div>
                        </c:forEach>
                    </div>
                    <h4><span style="color: red"><b>
                        <fmt:message bundle="${msg}" key="to.pay"/>
                    </b> -   <fmt:formatNumber value="${currency * requestScope.price / 1000}" type="currency"/>
                    </span></h4>
                </div>
                <div class="col-sm-5" style="border: 1px solid gainsboro">
                    <div class="route-table">
                        <c:forEach items="${sessionScope.ticket.tour.routes}" var="route">
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
                <div class="col-sm-1"></div>
            </div>
            <div class="col-sm-12">
                <hr />
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                    <form action="buy" method="post">
                        <div class="form-group">
                            <label for="card"> <fmt:message bundle="${msg}" key="card_number"/>
                                VIZA\Specialistcard<br>
                                <fmt:message bundle="${msg}" key="card.number"/>
                                <span style="color:red">*</span>
                                <input required class="form-control" id="card"
                                       pattern="[1-9]{1}[0-9]{3,9}"
                                       title="<fmt:message bundle="${msg}" key="card.number"/>"
                                       minlength="4" maxlength="12" name="card"/>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV2\CVC<br>
                                <fmt:message bundle="${msg}" key="cvv.number"/>
                                <span style="color:red">*</span>
                                <input required class="form-control" id="cvv"
                                      pattern="[1-9]{1}\d{2}"
                                       minlength="3" maxlength="3"
                                       title="<fmt:message bundle="${msg}" key="cvv.number"/>"
                                       name="cvv" type="password">
                            </label>
                        </div>
                        <input type="hidden" value="${requestScope.price}" name="price">
                        <div class="form-group">
                            <button class="btn btn-lg btn-success">
                                <fmt:message bundle="${msg}" key="buy"/>
                            </button>
                        </div>
                        <div class="form-group" style="color:red; font-weight: bold;">
                            <c:forEach items="${requestScope.wrong}" var="i">
                                <fmt:message bundle="${msg}" key="${i}"/> <br>
                            </c:forEach>
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
