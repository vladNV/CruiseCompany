<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Add cruise</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<c:set var="ports" scope="request" value="${sessionScope.buildTour.ports}"/>
<script>
    var MAX_ROUTE = 5;
    var current = 0;
</script>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="container content">
        <div class="col-sm-6">
            <form method="post" action="${pageContext.request.contextPath}/addCruise">
                <div class="form-group">
                    <label for="tourName">Tour name:</label>
                    <input required class="form-control" pattern="[A-za-z\s-,\.]{5,200}"
                           name="name" id="tourName">
                </div>
                <div class="form-group">
                    <label for="region">Region:</label>
                    <input required class="form-control"
                           pattern="[A-Za-z-,\.;:0-9\s]{5,100}"
                           name="region" id="region">
                </div>
                <hr>
                    <h3 style="display: inline">Routes</h3>
                    <button type="button" class="btn btn-success"
                            id="route-but" onclick="add()">
                        Add route</button>
                    <table style="margin: 5% 0;"
                           class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Route name</th>
                            <th>Departure</th>
                            <th>Arrival</th>
                            <th>Port</th>
                        </tr>
                        </thead>
                        <tbody id="route">
                        </tbody>
                    </table>
                <hr>
                <div class="form-group">
                    <button class="btn btn-lg btn-success">
                        Add
                    </button>
                </div>
                <div class="form-group" style="color:red; font-weight: bold;">
                    <c:forEach items="${requestScope.wrong}" var="i">
                        <fmt:message bundle="${msg}" key="${i}"/> <br>
                    </c:forEach>
                </div>
            </form>
            <hr>
        </div>
        <div class="col-sm-6"></div>
    </div>
    <script type="text/javascript">
        function add(){
            var route = document.getElementById("route");
            var routeName = document.createElement("input");
            var departure = document.createElement("input");
            var arrival = document.createElement("input");
            var row = document.createElement("tr");
            var select = document.createElement("select");
            var defaultOption = document.createElement("option");

            var portsId = [
                <c:forEach items="${requestScope.ports}" var="port" varStatus="currentStatus">
                "${port.id}"
                <c:if test="${not currentStatus.last}">
                ,
                </c:if>
                </c:forEach>
            ];
            var ports = [
                <c:forEach items="${requestScope.ports}" var="port" varStatus="currentStatus">"${port.name} - ${port.country}"
                <c:if test="${not currentStatus.last}">
                ,
                </c:if>
                </c:forEach>
            ];

            defaultOption.selected = true;
            defaultOption.disabled = true;
            defaultOption.text = "Empty option";
            select.appendChild(defaultOption);
            for (i = 0; i < ports.length; i++) {
                var option = document.createElement("option");
                option.value = portsId[i];
                option.text = ports[i];
                select.appendChild(option);
            }

            select.name = "port";
            routeName.name = "routeName";
            departure.name = "departure";
            departure.type = "datetime-local";
            arrival.name = "arrival";
            arrival.type = "datetime-local";
            routeName.className = "form-control";
            departure.className = "form-control";
            arrival.className = "form-control";
            routeName.placeholder = "Route name";
            routeName.required = true;
            departure.required = true;
            arrival.required = true;
            routeName.style.width = "300px";
            departure.setAttribute("min",
                "<%=LocalDateTime.now().withNano(0).withSecond(0)%>");
            arrival.setAttribute("min",
                "<%=LocalDateTime.now().withNano(0).withSecond(0)%>");
            var cell = [];
            for (i = 0; i < 4; i++) {
                cell[i] = document.createElement("td");
            }
            cell[0].appendChild(routeName);
            cell[1].appendChild(departure);
            cell[2].appendChild(arrival);
            cell[3].appendChild(select);
            for (i = 0; i < 4; i++) {
                row.appendChild(cell[i]);
            }
            route.appendChild(row);
            ++current;
            if (current === MAX_ROUTE){
                document.getElementById("route-but").disabled = true;
            }
        }
    </script>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
