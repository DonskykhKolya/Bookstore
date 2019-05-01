<div class="container">

	<div class="row">

		<div class="col-md-2">
			<%@include file="share/sidebar.jsp"%>
		</div>

		<div class="col-md-10">
			<div class="row carousel-holder">
				<div class="col-md-10">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img class="slide-image" src="${contextRoot }/static/images/bookbanner1.jpg" alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${contextRoot }/static/images/bookbanner2.jpg" alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${contextRoot }/static/images/bookbanner3.jpg" alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${contextRoot }/static/images/bookbanner4.jpg" alt="">
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev"></a>
						<a class="right carousel-control" href="#carousel-example-generic" data-slide="next"></a>
					</div>
				</div>

			</div>

			<div class="col-sm-4 col-lg-4 col-md-4">
				<h4>Checkout more books!</h4>
				<hr/>
				<a class="btn btn-default btn-sm" href="${contextRoot}/show/all/books">More books</a>
			</div>
		</div>
	</div>
</div>
