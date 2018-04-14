<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <form method="post">
        Name    : <input type="text" id="login-form-username" name="name"/>
       Password : <input type="password" id="login-form-password" name="password" />
        <input type="submit" id="login-form-submit" />
        <label style="color: #ff0000;font-size: 18px;">${error}</label>
    </form>
</div>
<%@ include file="common/footer.jspf"%>