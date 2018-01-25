<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="jt" %>
<c:set var="locale" scope="session"
       value="${sessionScope.locale}" />
<fmt:setLocale value="${locale}"/>
<fmt:setBundle  basename="messages" var="msg"/>
<%@ page isELIgnored="false" %>
