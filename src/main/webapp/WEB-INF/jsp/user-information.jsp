<%@include file="includes/header.jsp"%>

<div class="panel panel-default">
  <div class="panel-heading">
    Profile
  </div>

  <div class="panel-body">
    <dl class="dl-horizontal">
      <dt>Name</dt>
      <dd><c:out value="${user.name}"/></dd>

      <dt>Email</dt>
      <dd><c:out value="${user.email}"/></dd>

      <dt>Roles</dt>
      <dd><c:out value="${user.roles}"/></dd>
    </dl>
  </div>

  <c:if test="${user.isEditableInCurrentSession()}">

    <div class="panel-footer">
      <a class="btn btn-default" href="/users/${user.id}/edit" role="button">Edit name</a>
      <%--<a class="btn btn-default" href="/users/${user.id}/change-password" role="button">Change password</a>--%>
      <%--<a class="btn btn-default" href="/users/${user.id}/change-email" role="button">Change email</a>--%>
    </div>

  </c:if>

</div>

<%@include file="includes/footer.jsp"%>