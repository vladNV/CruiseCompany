<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp"%>
<fmt:bundle basename="messages">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/main"><fmt:message key="tours"/></a></li>
            <li><a href="#"><fmt:message key="excurs"/></a></li>
            <li><a href="#"><fmt:message key="buy_ticket"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li><a href="${pageContext.request.contextPath}/registration">
                        <span class="glyphicon glyphicon-user"></span>
                        <fmt:message key="sign_up"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/login">
                        <span class="glyphicon glyphicon-log-in"></span>
                        <fmt:message key="sign_in"/>
                    </a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/profile">
                        <span class="glyphicon glyphicon-user"></span>
                        <fmt:message key="profile"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">
                        <span class="glyphicon glyphicon-log-in"></span>
                        <fmt:message key="sign_out"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</fmt:bundle>