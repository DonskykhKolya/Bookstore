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
					<%--@elvariable id="billing" type="ua.donskykh.mybookstore.domain.Address"--%>
					<sf:form method="POST" class="form-horizontal" id="billingForm"
						modelAttribute="billing">
						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineOne">Address Line One</label>
							<div class="col-md-8">
								<sf:input path="addressLineOne" type="text" class="form-control" placeholder="Enter address line one" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineTwo">Address Line Two</label>
							<div class="col-md-8">
								<sf:input path="addressLineTwo" type="text" class="form-control" placeholder="Enter address line two" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="city">City</label>
							<div class="col-md-8">
								<sf:input path="city" type="text" class="form-control" placeholder="Enter city" />
								<sf:errors path="city" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="postalCode">Postal Code</label>
							<div class="col-md-8">
								<sf:input path="postalCode" type="number" class="form-control" placeholder="XXXXXX" maxlength="6" />
								<sf:errors path="postalCode" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="region">Region</label>
							<div class="col-md-8">
								<sf:input path="region" type="text" class="form-control" placeholder="Enter region" />
								<sf:errors path="region" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="country">Country</label>
							<div class="col-md-8">
								<sf:input path="country" type="text" class="form-control" placeholder="Enter country" />
								<sf:errors path="country" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary" name="_eventId_personal">Previous</button>

								<button type="submit" class="btn btn-primary" name="_eventId_confirm">Next</button>

							</div>

						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../shared/flows-footer.jsp"%>