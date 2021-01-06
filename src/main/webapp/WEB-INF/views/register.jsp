<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registration</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>
	<body>
		<form:form id="regForm" modelAttribute="user" action="register/submit" method="post" data-validation="register/validation">
			<div class="col-md-4 mb-3">
				<label for="username"><fmt:message key="register.username.label" /></label>
				<form:input path="login.username" id="username" type="text" class="form-control" placeholder="Username" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="password"><fmt:message key="register.password.label" /></label>
				<form:input path="login.password" id="password" type="text" class="form-control" placeholder="Password" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="firstname"><fmt:message key="register.firstname.label"><fmt:param value="1" /></fmt:message></label>
				<form:input path="firstname[0]" id="firstname1" type="text" class="form-control" placeholder="First name 1" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="firstname"><fmt:message key="register.firstname.label"><fmt:param value="2" /></fmt:message></label>
				<form:input path="firstname[1]" id="firstname2" type="text" class="form-control" placeholder="First name 2" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="lastname"><fmt:message key="register.lastname.label" /></label>
				<form:input path="lastname" id="lastname" type="text" class="form-control" placeholder="Last name" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="age"><fmt:message key="register.age.label" /></label>
				<form:input path="age" id="age" type="text" class="form-control" placeholder="Age" />
				<div class="invalid-feedback"></div>
			</div>
			<div class="col-md-4 mb-3">
				<input type="submit" class="btn btn-secondary w-100" value="Register">
			</div>
			<div class="col-md-4 mb-3">
				<a href="/SpringExample">Home</a>
			</div>
		</form:form>
	</body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/js/validation.js" />"></script>
</html>