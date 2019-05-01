<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <c:if test="${not empty message }">
            <div class="col-xs-12">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                        ${message }
                </div>
            </div>
        </c:if>

        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Book controlling</h4>
                </div>
                <div class="panel-body">
                    <%--@elvariable id="book" type="ua.donskykh.mybookstore.domain.Book"--%>
                    <sf:form class="form-horizontal" modelAttribute="book" action="/control/books" method="POST"
                             enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="control-label col-md-4" for="title">Enter book title : </label>
                            <div class="col-md-8">
                                <sf:input type="text" path="title" id="title" placeholder="Book title"
                                          class="form-control"/>
                                <sf:errors path="title" cssClass="help-block" element="em"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="authorName">Select author : </label>
                            <div class="col-md-8">
                                <sf:select path="authorName" id="authorName" class="form-control">
                                    <sf:options items="${authors }" itemLabel="name" itemValue="name"/>
                                </sf:select>
                                <c:if test="${book.id == null }">
                                    <div class="text-right">
                                        <br>
                                        <button type="button" class="btn btn-warning btn-xs" data-toggle="modal"
                                                data-target="#myAuthorModal">Add author
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="dateOfPublish">Date of publish : </label>
                            <div class="col-md-8">
                                <sf:input type="number" path="dateOfPublish" id="dateOfPublish"
                                          placeholder="Date of publish" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="description">Book description : </label>
                            <div class="col-md-8">
                                <sf:textarea path="description" id="description" row="4"
                                             placeholder="Write a description of Book" class="form-control"/>
                                <sf:errors path="description" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="price">Enter price : </label>
                            <div class="col-md-8">
                                <sf:input type="number" path="price" id="price" placeholder="Price in &#36"
                                          class="form-control"/>
                                <sf:errors path="price" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="quantity">Quantity Available : </label>
                            <div class="col-md-8">
                                <sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available"
                                          class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="file">Select an image : </label>
                            <div class="col-md-8">
                                <sf:input type="file" path="file" id="file" class="form-control"/>
                                <sf:errors path="file" cssClass="help-block" element="em"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="categoryId">Select category : </label>
                            <div class="col-md-8">
                                <sf:select path="categoryId" id="categoryId" class="form-control" items="${categories }"
                                           itemLabel="name" itemValue="id"/>
                                <c:if test="${book.id == null }">
                                    <div class="text-right">
                                        <br>
                                        <button type="button" class="btn btn-warning btn-xs" data-toggle="modal"
                                                data-target="#myCategoryModal">Add category
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Submit"
                                       class="btn btn-primary"/>
                                <sf:hidden path="id"/>
                                <sf:hidden path="code"/>
                                <sf:hidden path="supplierId"/>
                                <sf:hidden path="purchases"/>
                                <sf:hidden path="active"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h3>Available books</h3>
            <hr/>
        </div>
        <div class="col-xs-12">
            <div class="container-fluid">
                <div class="table-responsive">
                    <table id="adminBooksTable" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th></th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Date of publish</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Active</th>
                            <th>Edit</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Id</th>
                            <th></th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Date of publish</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Active</th>
                            <th>Edit</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">Add new category</h4>
                </div>
                <div class="modal-body">
                    <%--@elvariable id="category" type="ua.donskykh.mybookstore.domain.Category"--%>
                    <sf:form id="categoryForm" modelAttribute="category" action="${contextRoot }/control/category"
                             method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label for="name" class="control-label col-md-4">Category : </label>
                            <div class="col-md-8">
                                <sf:input type="text" path="name" id="category_name" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="control-label col-md-4">Category description : </label>
                            <div class="col-md-8">
                                <sf:textarea cols="" rows="5" path="description" id="category_description"
                                             class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" value="Add Category" class="btn btn-primary"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myAuthorModal" role="dialog" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h4 class="modal-title">Add new author</h4>
                </div>
                <div class="modal-body">
                    <%--@elvariable id="author" type="ua.donskykh.mybookstore.domain.Author"--%>
                    <sf:form id="authorForm" modelAttribute="author" action="${contextRoot }/control/author"
                             method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label for="name" class="control-label col-md-4">Author : </label>
                            <div class="col-md-8">
                                <sf:input type="text" path="name" id="name" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" value="Add author" class="btn btn-primary"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
</div>
