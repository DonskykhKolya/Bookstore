<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Personal</h4>
				</div>
				<div class="panel-body">
					<%--@elvariable id="user" type="ua.donskykh.mybookstore.domain.User"--%>
					<sf:form method="POST" class="form-horizontal" id="registrationForm" modelAttribute="user">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Name</label>
							<div class="col-md-8">
								<sf:input path="name" type="text" class="form-control" placeholder="Name" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="surname">Surname</label>
							<div class="col-md-8">
								<sf:input path="surname" type="text" class="form-control" placeholder="Surname" />
								<sf:errors path="surname" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="email">Email</label>
							<div class="col-md-8">
								<sf:input path="email" type="email" class="form-control" placeholder="abc@xyz.com" />
								<sf:errors path="email" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="phone">Phone number</label>
							<div class="col-md-8">
								<sf:input path="phone" type="number" maxlength="10" class="form-control" placeholder="XXXXXXXXXX" />
								<sf:errors path="phone" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="password">Password</label>
							<div class="col-md-8">
								<sf:input path="password" type="password" class="form-control" placeholder="Password" />
								<sf:errors path="password" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="confirmPassword">Confirm password</label>
							<div class="col-md-8">
								<sf:input path="confirmPassword" type="password" class="form-control" placeholder="Re-Enter password" />
								<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<%--@declare id="role"--%><label class="control-label col-md-4" for="role">Select role</label>
							<div class="col-md-8">
								<label class="radio-inline"> <sf:radiobutton path="role" value="USER" checked="checked" /> User
								</label> <label class="radio-inline"> <sf:radiobutton path="role" value="SUPPLIER" /> Supplier </label>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary" name="_eventId_billing"> Next - Billing </button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../shared/flows-footer.jsp"%>