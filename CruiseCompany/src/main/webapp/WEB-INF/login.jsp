<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Authentication</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/static/menu.jsp"/>
<div class="wrapper">
<div class="container content">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <div class="login">
            <form action="login" method="post">
                <div class="form-group">
                    ${requestScope.status}
                </div>
                <div class="form-group">
                    <input required name="login" class="form-control"
                           placeholder="<fmt:message bundle="${msg}" key="email" />">
                </div>
                <div class="form-group">
                    <input type="password" required name="password" class="form-control"
                           placeholder="<fmt:message bundle="${msg}" key="password" />">
                </div>
                <div class="form-group">
                    <c:import url="/WEB-INF/static/requestStatus.jsp"/>
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