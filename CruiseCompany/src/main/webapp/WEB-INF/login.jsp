<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <title>Authentication</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="container">
    <div class="col-sm-offset-3">
        <div class="login">
            <form action="login" method="post">
                <input required name="login" class="form-control"
                       placeholder="<fmt:message key="email" />">
                <input required name="password" class="form-control"
                       placeholder="<fmt:message key="password" />">
                <c:import url="/WEB-INF/static/requestStatus.jsp"/>
                <button class="btn btn-primary"><fmt:message key="sign_in"/></button>
            </form>
        </div>
        <hr/>
    </div>
</div>
</body>
</html>
</fmt:bundle>