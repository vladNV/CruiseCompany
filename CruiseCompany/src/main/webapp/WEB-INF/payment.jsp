<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="taglib.jsp"%>
<html>
<head>
    <c:import url="/WEB-INF/static/head.jsp"/>
    <title>Title</title>
</head>
<body>
    <c:import url="/WEB-INF/static/menu.jsp"/>
    <div class="wrapper">
        <div class="content">
            <div class="col-sm-12">
                <div class="col-sm-2"></div>
                <div class="col-sm-8"></div>
                <div class="col-sm-2"></div>
            </div>
            <div class="col-sm-12">
                <hr />
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                    <form action="payment" method="post">
                        <div class="form-group">
                            <label for="card"> <fmt:message bundle="msg" key="card_number"/> VIZA\Specialistcard
                                <span style="color:red">*</span>
                                <input required class="form-control" id="card" name="card"/>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV2\CVC <span style="color:red">*</span>
                                <input required class="form-control" id="cvv" name="cvv" type="password">
                            </label>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-lg btn-success">
                                <fmt:message bundle="msg" key="buy"/>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6"></div>
            </div>
        </div>
    <c:import url="/WEB-INF/static/footer.jsp"/>
    </div>
</body>
</html>
