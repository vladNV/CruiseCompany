<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.01.2018
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="values">

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
</body>
</html>
