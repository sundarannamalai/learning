<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>

<div class="container">
    <h1>Your Todos</h1>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Completed?</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${todoList}" var="item">
                <tr>
                    <td>${item.description}</td>
                    <td><fmt:formatDate value="${item.targetDate}" pattern="dd/MM/yyyy"/></td>
                    <td>${item.completed}</td>
                    <td><a class="btn btn-success" href="/update-todo?id=${item.id}">Update</a></td>
                    <td><a class="btn btn-warning" href="/delete-todo?id=${item.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div><a class="button" href="/add-todo">Add a Todo</a></div>
</div>

<%@ include file="common/footer.jspf"%>