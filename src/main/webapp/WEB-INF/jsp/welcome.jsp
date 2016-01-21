<%@include file="includes/header.jsp"%>
<div class="jumbotron">
  <h2><spring:message code="welcome"/></h2>
  <p><spring:message code="introduction"/></p>
  <a class="btn btn-primary btn-lg" href=" <c:url value='/auth/register'/>" role="button">Register</a>
  <a class="btn btn-default btn-lg" href=" <c:url value='/auth/login'/>" role="button">Login</a>
</div>
<%@include file="includes/footer.jsp"%>
