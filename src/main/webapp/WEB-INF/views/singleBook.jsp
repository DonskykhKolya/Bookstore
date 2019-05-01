<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="/home">Home</a></li>
				<li><a href="/show/all/books">Books</a></li>
				<li class="active">${book.title }</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${contextRoot }/static/images/${book.code}.jpg"
					class="img img-responsive" />
			</div>
		</div>

		<div class="col-xs-12 col-sm-8">
			<h3>${book.title }</h3>
			<hr />
			<h4>${book.authorName }</h4>
			<hr />
			<p>${book.dateOfPublish }</p>
			<hr />
			<p>${book.description }</p>
			<hr />
			<h4>
				Price : <strong>&#36; ${book.price } /-</strong>
			</h4>
			<hr />


			<c:choose>
				<c:when test="${book.quantity < 1}">
					<h6>
						Qty. available : <span style="color: red;">Out of stock</span>
					</h6>
				</c:when>
				<c:otherwise>
					<h6>Qty. Available : ${book.quantity }</h6>
				</c:otherwise>
			</c:choose>

			<security:authorize access="hasAuthority('USER')">
				<c:choose>
					<c:when test="${book.quantity < 1 }">
						<a href="javascript:void(0);" class="btn btn-success disabled"><strike>&#128722; Add to shopping cart</strike></a>
					</c:when>
					<c:otherwise>
						<a href="${contextRoot }/shoppingCart/add/${book.id }/book" class="btn btn-success">&#128722; Add to shoppingCart</a>
					</c:otherwise>
				</c:choose>
			</security:authorize>
			
			<security:authorize access="hasAuthority('ADMIN')">
			
			<a href="${contextRoot }/control/${book.id }/books" class="btn btn-warning"><span
							class="glyphicon glyphicon-pencil"></span> Edit</a>
			</security:authorize>

			<a href="/show/all/books" class="btn btn-primary">Back</a>

		</div>
	</div>
</div>