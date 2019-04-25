<div class="container">
	<div class="row">
		<div class="col-md-3">
			<%@include file="share/sidebar.jsp"%>
		</div>
		<div class="col-md-9">
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllBooks == true}">
						<script>
							window.categoryId = '';
							window.authorName = '';
						</script>

						<ol class="breadcrumb">
							<li><a href="/home">Home</a></li>
							<li class="active"><span>All books</span></li>
						</ol>
					</c:if>

					<c:if test="${userClickCategoryBooks == true}">
					<script>
                        window.categoryId = '${category.id}';
					</script>
					<ol class="breadcrumb">
						<li><a href="/home">Home</a></li>
						<li class="active"><a href="#">Category</a></li>
						<li class="active"><span>${category.name }</span></li>
					</ol>
				    </c:if>

					<c:if test="${userClickAuthorBooks == true}">
						<script>
                            window.authorName = '${author.name}';
						</script>
						<ol class="breadcrumb">
							<li><a href="/home">Home</a></li>
							<li class="active"><a href="#">Authors</a></li>
							<li class="active"><span>${author.name}</span></li>
						</ol>
					</c:if>
				</div>
            </div>

			<div class="row">
				<div class="col-xs-12">
					<div class="container-fluid">
						<div class="table-responsive">
							<table id="bookListTable"
								class="table table-striped table-borderd">
								<thead>
									<tr>
										<th></th>
										<th>Title</th>
										<th>Author</th>
                                        <th>Date of publish</th>
										<th>Price</th>
										<th>Qty. Available</th>
										<th></th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th>Title</th>
										<th>Author</th>
                                        <th>Date of publish</th>
										<th>Price</th>
										<th>Qty. available</th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
