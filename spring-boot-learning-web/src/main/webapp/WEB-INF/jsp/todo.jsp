<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add a Todo</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <form:form method="post" modelAttribute="todo">
        <fieldset class="form-group">
            <form:label path="description">Description</form:label>
            <form:input type="text" path="description" class="form-control" required="required"/>
            <form:errors path="description" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="button btn-success">Add</button>
    </form:form>
</div>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js" type="javascript"> </script>
<script src="webjars/jquery/1.11.1/jquery.min.js" type="javascript"> </script>
</body>
</html>