<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextRoot }/home">MyBook Store</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="about"><a href="${contextRoot }/about">About</a></li>

				<li id="contact"><a href="${contextRoot }/contact">Contact</a></li>

				<li id="reviews"><a href="${contextRoot }/reviews">Reviews</a></li>

				<li id="listBooks"><a href="${contextRoot }/show/all/books">View books</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
					<li id="controlBooks"><a href="${contextRoot }/control/books">Control books</a></li>
				</security:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li id="controlBooks"><a href="${contextRoot}/registration">Sign up</a></li>

					<li id="controlBooks"><a href="${contextRoot }/login">Login</a></li>

				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userShoppingCart"><a
						class="btn btn-default dropdown-toggle" href="javascript:void(0)"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true"> ${userModel.fullName } <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<security:authorize access="hasAuthority('USER')">
								<li id="shoppingCart">
									<a href="${contextRoot}/shoppingCart/show"> &#128722;
									<span class="badge">${userModel.shoppingCart.booksInCart }</span> - &#36;${userModel.shoppingCart.total }
								</a></li>

								<li role="separator" class="divider"></li>
							</security:authorize>
							<li id="logout"><a href="${contextRoot}/logout">Logout</a></li>
						</ul></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
<script type="text/javascript">
window.userRole = '${userModel.role }';

</script>






