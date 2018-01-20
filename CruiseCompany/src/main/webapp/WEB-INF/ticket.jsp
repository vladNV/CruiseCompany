<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Ticket</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
<div class="content">
<div class="col-sm-12">
    <div class="col-sm-2"></div>
    <div class="info-page col-sm-8">
        <div>
            <h3><fmt:message bundle="${msg}" key="ticket_type"/></h3>
                ${sessionScope.ticket.type}
        </div>
        <div>
            <h3><fmt:message bundle="${msg}" key="departure"/></h3>
                ${sessionScope.ticket.departure}
        </div>
        <div>
            <h3><fmt:message bundle="${msg}" key="arrival"/></h3>
                ${sessionScope.ticket.arrival}
        </div>
        <div>
            <h3><fmt:message bundle="${msg}" key="price"/></h3>
                ${sessionScope.ticket.price}
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
<div class="col-sm-12">
    <hr />
    <div class="col-sm-2"></div>
    <div class="excursion-page col-sm-8">
        <div class="ticket-table">
            <div>
                <h4><fmt:message bundle="${msg}" key="short_desc_excurs"/></h4>
            </div>
            <div class="ticket-header">
                <div class="ticket-cell">
                    <fmt:message bundle="${msg}" key="excursion"/></div>
                <div class="ticket-cell">
                    <fmt:message bundle="${msg}" key="port"/></div>
                <div class="ticket-cell">
                    <fmt:message bundle="${msg}" key="country"/></div>
                <div class="ticket-cell">
                    <fmt:message bundle="${msg}" key="price"/></div>
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
</div>
<div class="col-sm-12">
    <hr />
    <div class="col-sm-2"></div>
    <div class="form-page col-sm-4">
            <form action="confirm" method="post">
                <div class="form-group">
                    <label for="phone">
                        <fmt:message bundle="${msg}" key="phone"/>
                        <span style="color:red">*</span>
                    </label>
                    <input id="phone" placeholder="" required name="phone" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="name">
                        <fmt:message bundle="${msg}" key="name"/>
                        <span style="color:red">*</span>
                    </label>
                    <input id="name" name="name" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="surname">
                        <fmt:message bundle="${msg}" key="surname"/>
                        <span style="color:red">*</span>
                    </label>
                    <input id="surname" name="surname" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="amount">
                        <fmt:message bundle="${msg}" key="passenger.amount"/>
                        <span style="color:red">*</span>
                    </label>
                    <select id="amount" name="amount">
                        <c:forEach begin="1" end="8" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary btn-lg">
                        <fmt:message bundle="${msg}" key="confirm"/>
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