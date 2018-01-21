<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp"%>
<fmt:bundle basename="messages">
<nav class="navbar navbar-inverse" style="border-radius: 0;">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/main"><fmt:message key="tours"/></a></li>
            <li><a href="#"><fmt:message key="excurs"/></a></li>
            <li><a href="#"><fmt:message key="buy_ticket"/></a></li>
            <li>
                <select name="language" style="margin-top: 16px;"
                        id="language" onchange="location = this.value;">
                    <option selected value=""><fmt:message key="language"/></option>
                    <option value="${pageContext.request.contextPath}/eng">ENG</option>
                    <option value="${pageContext.request.contextPath}/ua">UA</option>
                </select>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li><a href="${pageContext.request.contextPath}/registration">
                        <fmt:message key="sign_up"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/login">
                        <fmt:message key="sign_in"/>
                    </a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/profile">
                        <fmt:message key="profile"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">
                        <fmt:message key="sign_out"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</fmt:bundle>