<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp"%>
<%@ page isELIgnored="false" %>
<br />
<c:if test="${requestScope.wrong ne null}">
    <fmt:message bundle="${msg}" key="${requestScope.wrong}"/>
</c:if>
<br />
<c:if test="${requestScope.pageError ne null}">
    <fmt:message bundle="${msg}" key="${requestScope.pageError}"/>
</c:if>
<br />
