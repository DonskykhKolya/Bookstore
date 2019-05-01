<div class="btn-group-vertical" role="group" aria-label="...">
    <div class="btn-group">
        <button class="btn btn-default btn-md dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">Category <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <c:forEach items="${categories }" var="category">
                <a href="/show/category/${category.id }/books" class="list-group-item"
                   id="a_${category.name }">${category.name }</a>
            </c:forEach>
        </ul>
    </div>
    <div class="btn-group">
        <button class="btn btn-info btn-md dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">Authors <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <c:forEach items="${authors }" var="author">
                <a href="/show/author/${author.name }/books" class="list-group-item"
                   id="a_${author.name }">${author.name }</a>
            </c:forEach>
        </ul>
    </div>
</div>