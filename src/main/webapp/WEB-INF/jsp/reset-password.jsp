<%@include file="includes/header.jsp"%>
<div class="panel panel-primary">

  <div class="panel-heading">
    <h2 class="panel-title">Reset password</h2>
  </div>

  <div class="panel-body">
    <p><spring:message code="auth.resetPassword.description"/></p>

    <form:form modelAttribute="resetPasswordForm" role="form">

      <form:errors/>

      <div class="form-group">
        <form:label path="password">New password</form:label>
        <form:input path="password" type="password" class="form-control" placeholder="Enter new password" />
        <form:errors path="password" cssClass="error"/>
      </div>

      <div class="form-group">
        <form:label path="retypePassword">Confirm password</form:label>
        <form:input path="retypePassword" type="password" class="form-control" placeholder="Retype new password" />
        <form:errors path="retypePassword" cssClass="error"/>
      </div>
      <button type="submit" class="btn btn-primary">Reset password</button>
    </form:form>
  </div>
</div>
<%@include file="includes/footer.jsp"%>
