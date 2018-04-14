<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <form:form method="post" modelAttribute="todo">
        <form:hidden path="id"/>
        <fieldset class="form-group">
            <form:label path="description">Description</form:label>
            <form:input type="text" path="description" class="form-control" required="required"/>
            <form:errors path="description" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Target Date</form:label>
            <form:input type="text" path="targetDate" class="form-control" required="required"/>
            <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="button btn-success">Add</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>