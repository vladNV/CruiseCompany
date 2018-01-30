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
        <form class="form-group" action="signup" method="post">
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="login"/>"
                       minlength="3" maxlength="75" required
                       pattern="^[A-Za-z0-9_]{3,75}$"
                       class="form-control" title="" name="login">
            </div>
            <div class="form-group">
                <input type="email" placeholder="<fmt:message bundle="${msg}" key="email"/>"
                       minlength="5" maxlength="100" required
                       class="form-control" name="email">
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="password"/>"
                       minlength="4" maxlength="16" type="password" required
                       pattern="[A-Za-z0-9]{4,16}"
                       class="form-control" name="password"
                       title="<fmt:message bundle="${msg}" key="reg.pass"/>"/>
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message bundle="${msg}" key="repeat_password"/>"
                       minlength="4" maxlength="16" type="password" name="repassword"
                       pattern="[A-Za-z0-9]{4,16}"
                       title="<fmt:message bundle="${msg}" key="reg.pass"/>"
                       required class="form-control">
            </div>
            <div class="form-group" style="color:red; font-weight: bold;">
                <c:forEach items="${requestScope.wrong}" var="i">
                   <fmt:message bundle="${msg}" key="${i}"/> <br>
                </c:forEach>
            </div>
            <div class="form-group">
                <button class="btn btn-primary pull-right">
                    <fmt:message bundle="${msg}" key="sign_up"/>
                </button>
            </div>
        </form>
    </div>
    <div class="col-sm-3"></div>
</div>
<c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>