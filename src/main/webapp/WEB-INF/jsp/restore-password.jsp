<%@include file="includes/header.jsp"%>
<div class="panel panel-primary">

  <div class="panel-heading">
    <h2 class="panel-title">Restore password</h2>
  </div>

  <div class="panel-body">
    <p><spring:message code="auth.restorePassword.description"/></p>

    <form:form modelAttribute="restorePasswordForm" role="form">

      <form:errors/>

      <div class="form-group">
        <form:label path="email">Email address</form:label>
        <form:input path="email" type="email" class="form-control" placeholder="Enter email" />
        <form:errors path="email" cssClass="error"/>
      </div>
      <button type="submit" class="btn btn-primary">Restore password</button>
    </form:form>
  </div>
</div>
<%@include file="includes/footer.jsp"%>
