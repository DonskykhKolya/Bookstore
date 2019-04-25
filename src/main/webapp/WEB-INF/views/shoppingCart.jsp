<div class="container">

	<c:if test="${not empty message }">
		<div class="alert alert-info">
			<h3 class="text-center">${message }</h3>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${not empty booksInCart }">
			<table id="shoppingCart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 50%">Book</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 22%" class="text-center">Subtotal</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${booksInCart }" var="booksInCart">

						<tr>
							<td data-th="Book">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img
											src="${rootContext }/static/images/${booksInCart.book.code}.jpg"
											alt="${booksInCart.book.title }"
											class="img-responsive cartImg" />
									</div>
									<div class="col-sm-10">
										<h4 class="nomargin">${booksInCart.book.title }
											<c:if test="${booksInCart.available == false }">
												<strong class="unavailable">(Not available)</strong>
											</c:if>
										</h4>
										<p>Author - ${booksInCart.book.authorName }</p>
										<p>Description - ${booksInCart.book.description }</p>
									</div>
								</div>
							</td>
							<td data-th="Price">&#36; ${booksInCart.buyingPrice }</td>
							<td data-th="Quantity"><input type="number" class="form-control text-center" id="count_${booksInCart.id }" min="1" max="3" value="${booksInCart.bookCount }"></td>
							<td data-th="Subtotal" class="text-center">&#36; ${booksInCart.total }</td>
							<td class="actions" data-th="">
								<button type="button" name="refreshCart" value="${booksInCart.id }" class="btn btn-info btn-sm"> &#128472;</button>
								<a href="${contextRoot }/shoppingCart/${booksInCart.id}/delete" class="btn btn-danger btn-sm">&#11199;</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr class="visible-xs">
						<td class="text-center"><strong>Total &#36;
								${userModel.shoppingCart.total }</strong></td>
					</tr>
					<tr>
						<td><a href="${contextRoot }/show/all/books" class="btn btn-warning"> Continue shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>
						<td class="hidden-xs text-center"><strong>Total&#36; ${userModel.shoppingCart.total }</strong></td>
						<td><a href="#" class="btn btn-success btn-block">Checkout</a></td>
					</tr>
				</tfoot>

			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<div class="text-center">
					<h1>Your shopping cart is empty!</h1>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>