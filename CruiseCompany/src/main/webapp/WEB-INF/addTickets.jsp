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
            <form method="post" id="addTickets"
                  action="${pageContext.request.contextPath}/addTickets">
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
                <div id="values">

                </div>
                <div class="form-group">
                    <button class="btn btn-success">Accept</button>
                </div>

                <div class="form-group" style="color:red; font-weight: bold;">
                    <c:forEach items="${requestScope.wrong}" var="i">
                        <fmt:message bundle="${msg}" key="${i}"/> <br>
                    </c:forEach>
                </div>
            </form>
        </div>
        <script>
            var titles = ['STANDARD TICKET', 'PREMIUM TICKET', 'LUXE TICKET'];
            var form = document.getElementById('values');
            var type = ['standard','premium','luxe'];
            var bonus = ['beauty','pool','cinema',
                'sport','tennis','library'];
            for (i = 0; i < titles.length; i++) {
                var H4 = document.createElement('h4');
                H4.innerHTML = titles[i];
                form.appendChild(H4);

                var block = document.createElement('div');
                block.className = 'form-group';

                var label = document.createElement('label');
                label.innerText = 'Price in dollars';

                var input = document.createElement('input');
                input.required = true;
                input.name = 'price';
                input.type = 'number';
                input.className = 'form-control';
                input.pattern = '[1-9]{1}\\d{1,5}';

                label.appendChild(input);
                block.appendChild(label);
                form.appendChild(block);

                for (j = 0; j < bonus.length; j++) {
                    block = document.createElement('div');
                    block.className = 'form-group';

                    label = document.createElement('label');
                    label.innerText = bonus[j].toUpperCase();

                    input = document.createElement('input');
                    input.name = 'bonus';
                    input.value = type[i] + ',' + bonus[j];
                    input.className = 'form-check-input';
                    input.type = 'checkbox';

                    label.appendChild(input);
                    block.appendChild(label);
                    form.appendChild(block);
                }
            }
        </script>
        <div class="col-sm-6"></div>
    </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
</div>
</body>
</html>
