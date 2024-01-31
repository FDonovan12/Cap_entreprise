<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="d-flex">
    <h1 class="mb-2">${game.name}</h1>
    <div class="ps-3 fs-3">${jspUtils.getStringRating(reviewService.getRatingByObject(game))}</div>
</div>
<div class="row">
<%--    <div class="col-1"></div>--%>
    <div class="col-12 col-md-4 vstack">
        <img class="img-fluid" src="${game.image}"  alt="">
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <div class="d-flex justify-content-around">
                <a class="btn btn-success" href="${UrlRoute.URL_GAME_EDIT}/${game.id}">
                    <i class="fa-solid fa-pen"></i>
                </a>
                <a class="btn btn-danger" >
                    <i class="fa-regular fa-trash-can"></i>
                </a>
            </div>
        </security:authorize>
    </div>
    <div class="col-12 col-md-8">
        <div class="row grid gap-3">
            <div class="col-6 col-md-6 d-flex justify-content-end">Genre :</div>
            <span class="col" >
                <a class="px-1 border rounded" href="${UrlRoute.URL_GENRE}/${game.genre.id}">
                    ${game.genre.name}
                </a>
            </span>

            <div class="col-6 col-md-6 d-flex justify-content-end">Classification :</div>
            <span class="col" >
                <a class="px-1 border rounded" class="col" href="${UrlRoute.URL_CLASSIFICATION}/${game.classification.id}">
                    ${game.classification.name}
                </a>
            </span>

            <div class="col-6 col-md-6 d-flex justify-content-end">Publisher :</div>
            <span class="col" >
                <a class="px-1 border rounded" href="${UrlRoute.URL_PUBLISHER}/${game.publisher.id}">
                    ${game.publisher.name}
                </a>
            </span>

            <div class="col-6 col-md-6 d-flex justify-content-end">Modele economique :</div>
            <span class="col" >
                <a class="px-1 border rounded" href="${UrlRoute.URL_BUSINESSMODEL}/${game.businessModel.id}">
                    ${game.businessModel.name}
                </a>
            </span>

            <div class="col-6 col-md-6 d-flex justify-content-end">Platformes :</div>
            <div class="col">
                <c:forEach items="${game.platforms}" var="platform">
                    <a class="me-2 px-1 border rounded" href="${UrlRoute.URL_PLATFORM}/${platform.id}">${platform.name}</a>
                </c:forEach>
            </div>
            <div class="col-6 col-md-6 d-flex justify-content-end">Sortie :</div>
            <div class="col d-flex justify-content-start">${dateUtils.getDateFormat(game.publishedAt, "dd MMMM yyyy")}</div>
        </div>
    </div>
    <div>
        <h2 class="mb-3 mt-5">Description</h2>
        <p class="mb-3">${game.description}</p>
        <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW_NEW}/${game.id}">Laisser un avis sur ce jeu</a>
    </div>
    <h2>Commentaires</h2>
    <c:set var="reviews" scope="request" value="${reviews}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reviews_table.jsp"/>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
