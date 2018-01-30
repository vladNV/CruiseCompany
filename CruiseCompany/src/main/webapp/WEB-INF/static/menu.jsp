<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/taglib.jsp"%>

<nav class="navbar navbar-inverse" style="border-radius: 0;">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/main"><fmt:message bundle="${msg}" key="tours"/></a></li>
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li><a href="${pageContext.request.contextPath}/registration">
                        <fmt:message bundle="${msg}"  key="sign_up"/>
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/login">
                        <fmt:message bundle="${msg}"  key="sign_in"/>
                    </a></li>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/cruiseEditor">
                                Admin
                            </a>
                        </li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/profile">
                            ${sessionScope.user.email}
                    </a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">
                        <fmt:message bundle="${msg}"  key="sign_out"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
