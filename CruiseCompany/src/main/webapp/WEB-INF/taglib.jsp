<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="jt" %>
<c:set var="locale" scope="session"
       value="${sessionScope.locale}" />
<c:if test="${sessionScope.currency == null}">
    <c:set scope="session" value="1" var="currency"/>
</c:if>
<c:set var="currency" scope="session"
       value="${sessionScope.currency}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle  basename="messages" var="msg"/>
<%@ page isELIgnored="false" %>
