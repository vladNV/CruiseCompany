<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="taglib.jsp"%>
<html>
<head>
    <title>Add cruise</title>
    <c:import url="/WEB-INF/static/head.jsp"/>
</head>
<body>
<script>
    var MAX_ROUTE = 5;
    var current = 0;
</script>
<div class="wrapper">
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="container content">
        <div class="col-sm-6">
            <form method="post" action="${pageContext.request.contextPath}/add_cruise">
                <div class="form-group">
                    <label for="tourName">Tour name:</label>
                    <input required class="form-control" name="name" id="tourName">
                </div>
                <div class="form-group">
                    <label for="region">Region:</label>
                    <input required class="form-control" name="region" id="region">
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
                            <th>Date</th>
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
                <div class="form-group">
                    ${requestScope.wrong}
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
            var date = document.createElement("input");
            var row = document.createElement("tr");
            var select = document.createElement("select");
            var defaultOption = document.createElement("option");
            defaultOption.selected = true;
            defaultOption.disabled = true;
            defaultOption.text = "Empty option";
            select.appendChild(defaultOption);
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
            for (i = 0; i < ports.length; i++) {
                var option = document.createElement("option");
                option.value = portsId[i];
                option.text = ports[i];
                select.appendChild(option);
            }
            select.name = "port";
            routeName.name = "routeName";
            date.name = "date";
            date.type = "datetime-local";
            routeName.className = "form-control";
            date.className = "form-control";
            routeName.placeholder = "Route name";
            routeName.required = true;
            date.required = true;
            routeName.style.width = "300px";
            date.setAttribute("min",
                "<%=LocalDateTime.now().withNano(0).withSecond(0)%>");
            var cell = [];
            for (i = 0; i < 3; i++) {
                cell[i] = document.createElement("td");
            }
            cell[0].appendChild(routeName);
            cell[1].appendChild(date);
            cell[2].appendChild(select);
            for (i = 0; i < 3; i++) {
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
