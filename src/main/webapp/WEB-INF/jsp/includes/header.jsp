<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap -->
  <link href="/public/lib/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">

  <link href="/public/css/styles.css" rel="stylesheet">

  <title>Up and running with Spring Framework quickly</title>
</head>
<body>

<div class="container">
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/">Registration Demo</a>
      </div>

      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <sec:authorize access="isAnonymous()">
            <li><a href=" <c:url value='/auth/register'/>"><span class="glyphicon glyphicon-list-alt"></span> Register</a> </li>
            <li><a href=" <c:url value='/auth/login'/>"><span class="glyphicon glyphicon-log-in"></span> Login</a> </li>
          </sec:authorize>

          <sec:authorize access="isAuthenticated()">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                <span class="glyphicon glyphicon-user"></span>
                <sec:authentication property="principal.user.name" />  <span class="caret"></span>
              </a>
              <ul class="dropdown-menu">
                <li><a href="/users/<sec:authentication property='principal.user.id' />"><%--<span class="glyphicon glyphicon-user"></span>--%> Profile</a></li>
                <li><a href="/home">Project overview</a></li>
                <li role="separator" class="divider"></li>
                <li>
                  <c:url var="logoutUrl" value="/logout" />
                  <form:form	id="logoutForm" action="${logoutUrl}" method="post">
                  </form:form>
                  <a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
                </li>
              </ul>
            </li>
          </sec:authorize>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

  <sec:authorize access="hasRole('ROLE_UNVERIFIED')">
    <div class="alert alert-warning alert-dismissible" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <p><strong>Warning!</strong></p>
      <p><spring:message code="auth.verificationIsNotCompleted"/></p>
      <a href="/users/resend-confirmation-email" class="alert-link"><spring:message code="auth.newVerificationEmail"/></a>
    </div>
  </sec:authorize>

  <c:if test="${not empty flashMessage}">
    <div class="alert alert-${flashKind} alert-dismissable">
      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        ${flashMessage}
    </div>
  </c:if>