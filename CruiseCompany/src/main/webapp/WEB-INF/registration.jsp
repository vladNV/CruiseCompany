<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 18.01.2018
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<fmt:bundle basename="messages">
<html>
<head>
    <title><fmt:message key="registration"/></title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="container">
    <div class="col-sm-3"></div>
    <div class="col-sm-6 registration">
        <form class="form-group" action="registration" method="post">
            <div class="form-group">
                <input placeholder="<fmt:message key="login"/>"
                       required class="form-control" name="login">
            </div>
            <div class="form-group">
                <input type="email" placeholder="<fmt:message key="email"/>"
                       required class="form-control" name="email">
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message key="password"/>" type="password"
                       required class="form-control" name=s"password">
            </div>
            <div class="form-group">
                <input placeholder="<fmt:message key="repeat_password"/>"
                       name="repassword" required class="form-control">
            </div>
            <c:import url="/WEB-INF/static/requestStatus.jsp"/>
            <div class="form-group">
                <button class="btn btn-primary pull-right"><fmt:message key="sign_up"/></button>
            </div>
        </form>
    </div>
    <div class="col-sm-3"></div>
</div>
</body>
</html>
</fmt:bundle>