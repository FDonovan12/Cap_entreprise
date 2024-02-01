<%@ include file="../tag.jsp" %>

<div class="row">
    ${jspUtils.getPagination(contents, currentUrl, currentQuery)}
    <c:forEach items="${contents.content}" var="content">
        <a class="col-12 col-md-6 col-lg-4 mt-2 main-horizontal-card" href="${contentUrl}/${content.id}">
            <div class="horizontal-card">
                <div class="horizontal-card-img d-flex">
                    <img class="img-fluid" alt="${content.name}" src="${content.image}">
                </div>
                <div class="d-flex justify-content-between">
                    <p>${content.name}</p>
                    <p>${content.games.size()} jeux</p>
                    <p>Note moyenne ${jspUtils.getStringRating(reviewService.getRatingByObject(content))}</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>