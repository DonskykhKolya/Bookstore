<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>MyBook Store - ${title }</title>
<script type="text/javascript">
	window.menu = '${title }';
	window.contextRoot = '${contextRoot}'
</script>

<link href="${contextRoot }/static/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextRoot }/static/css/bootstrap-readable-theme.css"
	rel="stylesheet">
<link href="${contextRoot }/static/css/custom.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot }/home">MyBook Store</a>
				</div>
			</div>
		</nav>
		<div class="content">
			<div class="container">
				<c:if test="${not empty message }">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
							<div class="alert alert-danger">${message }</div>
						</div>
					</div>

				</c:if>
				<c:if test="${not empty logout }">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
							<div class="alert alert-success">${logout }</div>
						</div>
					</div>

				</c:if>

				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Login</h4>
							</div>
							<div class="panel-body">
								<form action="${contextRoot }/login" method="post"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<%--@declare id="username"--%><label for="username" class="col-md-4 control-label">Email: </label>
										<div class="col-md-8">
											<input type="email" name="username" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<%--@declare id="password"--%><label for="password" class="col-md-4 control-label">Password: </label>
										<div class="col-md-8">
											<input type="password" name="password" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="submit" name="password" class="btn btn-primary" />
											<input type="hidden" name="${_csrf.parameterName }"
												value="${_csrf.token }" />
										</div>
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<div class="text-right">
									New User - <a href="${contextRoot }/registration">Registration here</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%@include file="share/footer.jsp"%>

		<script src="${contextRoot }/static/js/jquery.js"></script>

		<script src="${contextRoot }/static/js/jquery.validate.js"></script>

		<script src="${contextRoot }/static/js/bootstrap.min.js"></script>

		<script src="${contextRoot }/static/js/custom.js"></script>
	</div>
</body>

</html>

