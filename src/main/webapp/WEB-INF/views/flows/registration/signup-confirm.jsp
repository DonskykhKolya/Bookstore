<%@include file="../shared/flows-header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Personal details</h4>
				</div>
				<div class="panel-body">
					<div class="text-center">
						<h4>Name           : ${registrationModel.user.name } ${registrationModel.user.surname }</h4>
						<h5>Email          : ${registrationModel.user.email }</h5>
						<h5>Contact Number : ${registrationModel.user.phone }</h5>
						<h5>Role           : ${registrationModel.user.role }</h5>
					</div>
				</div>
				<div class="panel-footer">
					<a href="${flowExecutionUrl }&_eventId_personal"
					class="btn btn-primary">Edit</a>
				</div>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Address</h4>
				</div>
				<div class="panel-body">
					<div class="text-center">
						<h4>${registrationModel.billing.addressLineOne }</h4>
						<h4>${registrationModel.billing.addressLineTwo }</h4>
						<h4>${registrationModel.billing.city }-${registrationModel.billing.postalCode }</h4>
						<h4>${registrationModel.billing.region }-${registrationModel.billing.country }</h4>
					</div>
				</div>
				<div class="panel-footer">
					<a href="${flowExecutionUrl }&_eventId_billing"
					class="btn btn-primary">Edit</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<div class="text-center">
				<a href="${flowExecutionUrl }&_eventId_submit"
					class="btn btn-primary">Confirm</a>

			</div>
		</div>
	</div>
</div>









<%@ include file="../shared/flows-footer.jsp"%>