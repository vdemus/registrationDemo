<%@include file="includes/header.jsp"%>

<div class="panel panel-default">

  <div class="panel-heading">
    <h3 class="panel-title">Profile</h3>
  </div>

  <div class="panel-body">

    <form:form modelAttribute="userEditForm" class="form-horizontal" role="form">

      <div class="form-group">
        <form:label path="name" class="col-lg-2 control-label">Name</form:label>
        <div class="col-lg-10">
          <form:input path="name" class="form-control" placeholder="Roles" />
          <form:errors path="name" cssClass="error" />
          <p class="help-block">Enter your display name.</p>
        </div>
      </div>

      <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="form-group">
          <form:label path="roles" class="col-lg-2 control-label">Roles</form:label>
          <div class="col-lg-10">
            <form:input path="roles" class="form-control" placeholder="Roles" />
            <form:errors path="roles" cssClass="error"/>
            <p class="help-block">Enter the roles of the user, separated by commas</p>
          </div>
        </div>
      </sec:authorize>

      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <button type="submit" class="btn btn-primary">Update</button>
        </div>
      </div>

    </form:form>

  </div>

</div>

<%@include file="includes/footer.jsp"%>
	