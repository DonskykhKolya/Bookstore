<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">

        <div class="tbl">
            <div class="list-group">
                <div class="col-sm-12">
                    <div id="comms">
                        <%--@elvariable id="reviews" type="ua.donskykh.mybookstore.domain.CustomerReviews"--%>
                        <c:forEach items="${reviews}" var="review" varStatus="itr">
                            <div class="list-group-item">
                                    <div class="user_name">
                                        <strong class="list-group-item-heading">${review.name}</strong>
                                    </div>
                                    <div class="review_text">
                                        <blockquote>
                                            <p class="list-group-item-text-"><small>${review.review}</small></p>
                                        </blockquote>
                                    </div>
                                    <div class="rate_us">
                                        <i>Rating: </i>${review.rating}
                                    </div>
                                </div>
                        </c:forEach>
                    </div>
                </div>
                <nav aria-label="Navigation">
                    <ul class="pagination pagination-sm">
                        <c:if test="${all ne null}">
                            <c:forEach var="i" begin="1" end="${all}">
                                <li><a href="/reviews?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </nav>
            </div>

            <security:authorize access="isAuthenticated()">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h5>Add review</h5>
                    </div>
                    <div class="panel-body">
                        <form action="/addRewiew" id="review_form" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="name" class="control-label col-md-4">Input your name</label>
                                <div class="col-md-8">
                                    <input type="text" id="name" placeholder="Name" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="review" class="control-label col-md-4">Review</label>
                                <div class="col-md-8">
                                <textarea name="review" id="review" placeholder="Text"
                                          class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="rating" class="control-label col-md-8">Rate us</label>
                                <div class="col-md-2">
                                    <select name="rating" id="rating" class="form-control">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button" class="btn btn-warning" id="review_submit">Post</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </security:authorize>
    </div>
</div>
<div class="hidden">
    <li class="list-group-item">
        <div class="li_content">
            <div class="user_name"></div>
            <div class="review_text"></div>
            <div class="rate_us"></div>
        </div>
    </li>
</div>
