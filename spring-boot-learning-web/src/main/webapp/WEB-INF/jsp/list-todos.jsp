<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo Listing of ${name}</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <h1>Your Todos</h1>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Completed?</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${todoList}" var="item">
                <tr>
                    <td>${item.description}</td>
                    <td>${item.targetDate}</td>
                    <td>${item.completed}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div><a class="button" href="/add-todo">Add a Todo</a></div>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js" type="javascript"> </script>
    <script src="webjars/jquery/1.11.1/jquery.min.js" type="javascript"> </script>
</div>
</body>
</html>