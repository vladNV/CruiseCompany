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
                    <label for="tourName">Tour name<br>
                        Use only latin symbols, numbers and '.,;:-!'</label>
                    <input required class="form-control" pattern="[A-za-z0-9\s\-,\.;:!\']{5,199}"
                           name="name" id="tourName"
                           minlength="5" maxlength="199"
                           title="Use only latin symbols, numbers and '.,;:-!'">
                </div>
                <div class="form-group">
                    <label for="region">Region<br>
                        Use only latin symbols, space and '-'</label>
                    <input required class="form-control"
                           minlength="3" maxlength="99"
                           pattern="[A-za-z\s\-]{3,99}"
                           title="Use only latin symbols, space and '-'"
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
                            <th></th>
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
            if (current === MAX_ROUTE){
                alert('Maximum ' + MAX_ROUTE + ' routes!');
                return;
            }
            // initializes
            var route = document.getElementById("route");
            var routeName = document.createElement("input");
            var departure = document.createElement("input");
            var arrival = document.createElement("input");
            var row = document.createElement("tr");
            var select = document.createElement("select");
            var defaultOption = document.createElement("option");
            var removeBut = document.createElement("button");
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

            row.id = 'row' + current;
            // select element
            select.name = "port";
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

            routeName.name = "routeName";
            routeName.placeholder = "Route name";
            routeName.required = true;
            routeName.className = "form-control";
            routeName.style.width = "300px";
            routeName.minLength = 3;
            routeName.maxLength = 149;
            routeName.pattern = "[A-Za-z\-,\s]{3, 149}";
            routeName.title = "Use only latin letters, space and '-' !";

            departure.name = "departure";
            departure.type = "datetime-local";
            departure.className = "form-control";
            departure.required = true;
            departure.setAttribute("min",
                "<%=LocalDateTime.now().withNano(0).withSecond(0)%>");

            arrival.type = "datetime-local";
            arrival.name = "arrival";
            arrival.className = "form-control";
            arrival.required = true;
            arrival.setAttribute("min",
                "<%=LocalDateTime.now().withNano(0).withSecond(0)%>");

            removeBut.className = "btn btn-danger";
            removeBut.appendChild(document.createTextNode("remove route"));
            removeBut.type = "button";
            removeBut.onclick = function () {
                document.getElementById('row' + (--current)).remove();
            };

            var cell = [];
            for (var i = 0; i < 5; i++) {
                cell[i] = document.createElement("td");
            }
            cell[0].appendChild(routeName);
            cell[1].appendChild(departure);
            cell[2].appendChild(arrival);
            cell[3].appendChild(select);
            cell[4].appendChild(removeBut);
            for (i = 0; i < 5; i++) {
                row.appendChild(cell[i]);
            }
            route.appendChild(row);
            ++current;
        }
    </script>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
