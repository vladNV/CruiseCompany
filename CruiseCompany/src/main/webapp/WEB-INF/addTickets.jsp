<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Add routes</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:set value="${sessionScope.buildTour.ships}" var="ships" scope="request"/>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="container content">
        <div class="col-sm-6">
            <form method="post" action="${pageContext.request.contextPath}/addTickets">
                <div class="form-group">
                    <label>Select free ship:
                        <select class="form-control" name="ship">
                            <c:forEach items="${requestScope.ships}" var="ship">
                                <option
                                        value="${ship.id}">Ship - ${ship.name}, capacity - ${ship.capacity}
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
                <div class="form-group">
                    <h4>STANDARD TICKET:</h4>
                    <div class="form-group">
                        <label>Quantity
                            <input required name="quantity" type="number"
                                   class="form-control" pattern="[1-9]{1}[0-9]{2}">
                        </label>
                        <label>Price in dollars
                            <input required  name="price" pattern="[1-9]{1}\d{1,15}"
                                   type="number" class="form-control"/>
                        </label>
                        <input type="hidden" name="type"
                               class="form-control" value="standard"/>
                    </div>
                    <h4>PREMIUM TICKET:</h4>
                    <div class="form-group">
                        <label>Quantity
                            <input required  name="quantity" type="number"
                                   pattern="[1-9]{1}[0-9]{2}"
                                   class="form-control"/>
                        </label>
                        <label>Price in dollars
                            <input required  name="price" type="number"
                                   class="form-control"/>
                        </label>
                        <input type="hidden" name="type"
                               class="form-control" value="premium"/>
                    </div>
                    <h4>LUXE TICKET:</h4>
                    <div class="form-group">
                        <label>Quantity
                            <input required name="quantity" type="number"
                                   class="form-control"
                                   pattern="[1-9]{1}[0-9]{2}"/>
                        </label>
                        <label>Price in dollars
                            <input required name="price" type="number"
                                   pattern="[1-9]{1}\d{1,15}" class="form-control"/>
                        </label>
                        <input type="hidden" name="type"
                               class="form-control" value="luxe">
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-success">Accept</button>
                </div>
                <div class="form-group">
                    ${requestScope.wrong}
                </div>
            </form>
        </div>
        <div class="col-sm-6"></div>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
