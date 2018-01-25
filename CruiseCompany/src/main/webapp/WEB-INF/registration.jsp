<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="registration"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
<div class="container content">
    <div class="col-sm-3"></div>
    <div class="col-sm-6 registration">
        <form class="form-group" action="registration" method="post">
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="login"/>"
                       min="3" max="75" required class="form-control" name="login">
            </div>
            <div class="form-group">
                <input type="email" placeholder="<fmt:message bundle="${msg}" key="email"/>"
                       min="5" max="100" required class="form-control" name="email">
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="password"/>"
                       min="4" max="16" type="password" required class="form-control" name="password">
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="repeat_password"/>"
                       min="4" max="16" type="password" name="repassword" required class="form-control">
            </div>
            <div class="form-group">
            <c:if test="${requestScope.wrong != null}">
                <fmt:message bundle="${msg}" key="${requestScope.wrong}"/><br>
            </c:if>
            <c:if test="${requestScope.wrongEmail != null}">
                <fmt:message bundle="${msg}" key="${requestScope.wrongEmail}"/><br>
            </c:if>
            <c:if test="${requestScope.wrongPass != null}">
                <fmt:message bundle="${msg}" key="${requestScope.wrongPass}"/><br>
            </c:if>
            <c:if test="${requestScope.wrongLogin != null}">
                <fmt:message bundle="${msg}" key="${requestScope.wrongLogin}"/><br>
            </c:if>
            </div>
            <div class="form-group">
                <button class="btn btn-primary pull-right">
                    <fmt:message bundle="${msg}" key="sign_up"/></button>
            </div>
        </form>
    </div>
    <div class="col-sm-3"></div>
</div>
<c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>