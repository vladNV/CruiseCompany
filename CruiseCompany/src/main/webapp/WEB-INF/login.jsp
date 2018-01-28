<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="auth" /></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
<div class="container content">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <div class="login">
            <form action="signin" method="post">
                <div class="form-group">
                    <input required name="email" class="form-control"
                           minlength="3" maxlength="75"
                           placeholder="<fmt:message bundle="${msg}" key="email" />">
                </div>
                <div class="form-group">
                    <input type="password" required name="password" class="form-control"
                           minlength="4" maxlength="16" pattern="[A-Za-z0-9]{4,16}"
                           placeholder="<fmt:message bundle="${msg}" key="password" />">
                </div>
                <div class="form-group" style="color:red; font-weight: bold;">
                   <c:forEach items="${requestScope.wrong}" var="i">
                       <fmt:message bundle="${msg}" key="${i}"/> <br>
                   </c:forEach>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary"><fmt:message bundle="${msg}" key="sign_in"/></button>
                </div>
            </form>
        </div>
        <hr/>
    </div>
    <div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>