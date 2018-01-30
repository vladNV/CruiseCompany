<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title><fmt:message bundle="${msg}" key="tours"/></title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="wrapper">
    <div class="col-sm-12">
        <select name="language" style="margin-top: 16px;"
                id="language" onchange="location = this.value;">
            <option selected value=""><fmt:message bundle="${msg}"  key="language"/></option>
            <option value="${pageContext.request.contextPath}/eng">ENG</option>
            <option value="${pageContext.request.contextPath}/ua">UA</option>
        </select>
    </div>
    <div class="col-sm-12 center-block content">
        <h2><fmt:message bundle="${msg}" key="tours"/></h2>
        <div class="tour-table">
            <form method="post" action="${pageContext.request.contextPath}/search" class="form-inline">
                <input required class="form-control" name="search" placeholder="Search for region"/>
                <button class="btn btn-success"><fmt:message bundle="${msg}" key="search"/></button>
                <a href="${pageContext.request.contextPath}/main" class="btn btn-success">
                    <fmt:message bundle="${msg}" key="cancel"/>
                </a>
            </form>
            <div class="tour-header">
                <div class="tour-cell"><fmt:message bundle="${msg}" key="cruise_name"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="departure"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="region"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="duration"/></div>
                <div class="tour-cell"><fmt:message bundle="${msg}" key="ship"/></div>
            </div>
            <c:forEach items="${requestScope.tours}" var="tour">
                <a href="${pageContext.request.contextPath}/tour/${tour.id}" class="tour-row">
                    <div class="tour-cell">${tour.name}</div>
                    <div class="tour-cell">
                    <jt:format value="${tour.departure}" style="FS"/>
                    </div>
                    <div class="tour-cell">${tour.region}</div>
                    <div class="tour-cell">
                            ${tour.duration.days} <fmt:message bundle="${msg}" key="days"/>
                            ${tour.duration.hours} <fmt:message bundle="${msg}" key="hours"/>
                            ${tour.duration.minutes} <fmt:message bundle="${msg}" key="minutes"/>
                    </div>
                    <div class="tour-cell">${tour.ship.name}</div>
                </a>
            </c:forEach>
        </div>
        <div class="col-sm-12">
            <hr>
            <div class="col-ms-4">
                <c:forEach begin="1" end="${requestScope.page}" var="i">
                    <a href="${pageContext.request.contextPath}/${requestScope.pathPage}/${i}">${i}</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </c:forEach>
            </div>
            <div class="col-sm-8">

            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
    </div>
</body>
</html>