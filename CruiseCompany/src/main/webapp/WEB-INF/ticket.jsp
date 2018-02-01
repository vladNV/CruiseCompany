<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="ticket"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:set scope="session" var="tour" value="${sessionScope.cart.ticket.tour}"/>
<div class="wrapper">
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="content">
<div class="col-sm-12">
    <div class="col-sm-2"></div>
    <div class="info-page col-sm-4">
        <b><fmt:message bundle="${msg}" key="cruise_name"/>:</b>
        ${sessionScope.tour.name}<br>
        <b><fmt:message bundle="${msg}" key="region"/>:</b>
        ${sessionScope.tour.region}<br>
        <b><fmt:message bundle="${msg}" key="departure"/>:</b>
        ${sessionScope.tour.departure}<br>
        <b><fmt:message bundle="${msg}" key="arrival"/>:</b>
        ${sessionScope.tour.arrival}<br>
        <b><fmt:message bundle="${msg}" key="duration"/>:</b>
        ${sessionScope.tour.duration.days} <fmt:message bundle="${msg}" key="days"/>
        ${sessionScope.tour.duration.hours} <fmt:message bundle="${msg}" key="hours"/>
        ${sessionScope.tour.duration.minutes} <fmt:message bundle="${msg}" key="minutes"/><br>
        <b><fmt:message bundle="${msg}" key="ticket_type"/>:</b>
        ${sessionScope.cart.ticket.type}<br>
        <b><fmt:message bundle="${msg}" key="place"/>:</b>
        ${sessionScope.cart.ticket.place}<br>
        <b><fmt:message bundle="${msg}" key="price.person"/>:</b>
        <fmt:formatNumber value="${currency * sessionScope.cart.ticket.price / 1000}" type="currency"/>
        </div>
    <div class="col-sm-4">
        <h4><fmt:message bundle="${msg}" key="bonus"/> </h4>
        <c:choose>
            <c:when test="${empty sessionScope.cart.ticket.bonus}">
                <fmt:message bundle="${msg}" key="bonus.empty"/>
            </c:when>
            <c:otherwise>
                <c:forEach items="${sessionScope.cart.ticket.bonus}" var="bonus">
                    <fmt:message bundle="${msg}" key="${bonus.description}"/><br>
                </c:forEach>
            </c:otherwise>
        </c:choose>

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
            <div style="margin: 25px 0;">
                <c:if test="${requestScope.excursionStatus != null}">
                    <fmt:message bundle="${msg}" key="${requestScope.excursionStatus}"/>
                </c:if>
            </div>
            <c:forEach items="${sessionScope.tour.routes}" var="r">
            <h4>${r.port.name}</h4>
                <div class="ticket-header">
                    <div class="ticket-cell">
                        <fmt:message bundle="${msg}" key="excursion"/>
                    </div>
                    <div class="ticket-cell">
                        <fmt:message bundle="${msg}" key="price"/>
                    </div>
                    <div class="ticket-cell"></div>
                    <div class="ticket-cell"></div>
                </div>
                <c:forEach items="${r.port.excursions}" var="excursion">
                    <div class="ticket-row">
                        <div class="ticket-cell">${excursion.name}</div>
                        <div class="ticket-cell">
                            <fmt:formatNumber value="${currency * excursion.price / 1000}"
                                              type="currency"/>
                        </div>
                        <div class="ticket-cell">
                            <form id="form_add_${excursion.id}" method="post"
                                  action="${pageContext.request.contextPath}/ticket/excursion">
                                <input type="hidden" name="id" id="idAdd" value="${excursion.id}">
                                <input type="hidden" name="command" value="add">
                                <button id="add_${excursion.id}" class="btn-link"
                                        onclick="add('${excursion.id}')">
                                <span style="color:green;">
                                    <fmt:message bundle="${msg}" key="add"/>
                                </span>
                                </button>
                            </form>
                        </div>
                        <div class="ticket-cell">
                            <form id="form_remove_${excursion.id}" method="post"
                                  action="${pageContext.request.contextPath}/ticket/excursion">
                                <input type="hidden" name="id" id="idRemove" value="${excursion.id}">
                                <input type="hidden" name="command" value="remove">
                                <button id="remove_${excursion.id}"
                                        class="btn-link"
                                        onclick="remove('${excursion.id}')">
                                    <span style="color:green;"><fmt:message bundle="${msg}" key="remove"/></span>
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
            <script type="text/javascript">
                function add(id) {
                    document.getElementById('remove_' + id).disabled = true;
                    document.getElementById('form_add_' + id).submit();
                }

                function remove(id) {
                    document.getElementById('remove_' + id).disabled = false;
                    document.getElementById('form_remove_' + id).submit();
                }
            </script>
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
<div class="col-sm-12">
    <hr />
    <div class="col-sm-2"></div>
    <div class="form-page col-sm-4">
        <form action="${pageContext.request.contextPath}/ticket/confirm" method="post">
            <div class="form-group">
                <label for="phone">
                    <fmt:message bundle="${msg}" key="phone"/><br>
                    <fmt:message bundle="${msg}" key="confirm.phone"/>
                    <span style="color:red">*</span>
                </label>
                <input id="phone" required
                       pattern="\+?[1-9]{1}\d{8,13}"
                       title="<fmt:message bundle="${msg}"
                       key="confirm.phone"/>"
                       minlength="9" maxlength="13"
                       name="phone" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="name">
                    <fmt:message bundle="${msg}" key="name"/>
                    <span style="color:red">*</span>
                </label>
                <input minlength="2" maxlength="100" id="name"
                       pattern="^[A-Za-z\-]{2,100}$"
                       title="<fmt:message bundle="${msg}"
                       key="confirm.name"/>" required
                       name="name" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="surname">
                    <fmt:message bundle="${msg}" key="surname"/>
                    <span style="color:red">*</span>
                </label>
                <input minlength="2" maxlength="100" id="surname"
                       pattern="^[A-Za-z\-]{2,100}$"
                       title="<fmt:message bundle="${msg}"
                       key="confirm.name"/>" required
                       name="surname" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="amount">
                    <fmt:message bundle="${msg}" key="passenger.amount"/>
                    <span style="color:red">*</span>
                </label>
                <select id="amount" name="amount">
                    <c:forEach begin="1" end="4" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-lg">
                    <fmt:message bundle="${msg}" key="confirm"/>
                </button>
            </div>
            <div class="form-group" style="color:red; font-weight: bold;">
                <c:forEach items="${requestScope.wrong}" var="i">
                   <fmt:message bundle="${msg}" key="${i}"/> <br>
                </c:forEach>
            </div>
        </form>
    </div>
    <div class="col-sm-4">
        <h4><fmt:message bundle="${msg}" key="excurs"/></h4>
        <div class="route-table">
            <div class="route-header">
                <div class="route-cell"><fmt:message bundle="${msg}" key="excursion"/></div>
                <div class="route-cell"><fmt:message bundle="${msg}" key="price"/> </div>
            </div>
            <c:forEach items="${sessionScope.cart.excursions}" var="ex">
                <div class="route-row">
                    <div class="route-cell">${ex.name}</div>
                    <div class="route-cell">
                        <fmt:formatNumber value="${currency * ex.price / 1000}"
                        type="currency"/></div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
</div>
<c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>