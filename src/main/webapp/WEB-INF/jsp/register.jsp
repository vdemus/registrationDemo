<%@include file="includes/header.jsp"%>
<div class="panel panel-primary">

  <div class="panel-heading">
  <h2 class="panel-title">Please register</h2>
  </div>

  <div class="panel-body">

    <form:form modelAttribute="registrationForm" role="form">

      <form:errors/>

      <div class="form-group">
        <form:label path="name">Name</form:label>
        <form:input path="name" class="form-control" placeholder="Your name" />
        <form:errors path="name" cssClass="error"/>
        <p class="help-block">Enter your display name.</p>
      </div>

      <div class="form-group">
        <form:label path="email">Email address</form:label>
        <form:input path="email" type="email" class="form-control" placeholder="Enter email" />
        <form:errors path="email" cssClass="error"/>
        <p class="help-block">Enter a unique email address. It will also be your login id.</p>
      </div>

      <div class="form-group">
        <form:label path="password">Password</form:label>
        <form:password path="password" class="form-control" placeholder="Password" />
        <form:errors path="password" cssClass="error"/>
        <p class="help-block">Password have to be at least 6 characters long.</p>
      </div>

      <button type="submit" class="btn btn-primary">Register</button>

    </form:form>
  </div>
</div>
<%@include file="includes/footer.jsp"%>
