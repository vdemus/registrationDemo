<%@include file="includes/header.jsp"%>
<div class="jumbotron">
  <h2><spring:message code="welcome"/></h2>
  <p><spring:message code="introduction"/></p>
  <p><a class="btn btn-primary btn-lg" href=" <c:url value='/auth/register'/>" role="button">Register</a></p>
</div>
<%@include file="includes/footer.jsp"%>
