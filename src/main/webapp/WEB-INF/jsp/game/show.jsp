<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="game_show"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1 class="mb-2">${game.name}</h1>
<div class="col-12">
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a class="btn btn-success" href="${UrlRoute.URL_GAME_EDIT}/${game.id}">
            <i class="fa-solid fa-pen"></i>
        </a>
        <a class="btn btn-danger" >
            <i class="fa-regular fa-trash-can"></i>
        </a>
    </security:authorize>
    <div class="row">
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Genre :</div>
            <div class="col d-flex justify-content-start">${game.genre.name}</div>
        </div>
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Classification :</div>
            <div class="col d-flex justify-content-start">${game.classification.name}</div>
        </div>
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Publisher :</div>
            <div class="col d-flex justify-content-start">${game.publisher.name}</div>
        </div>
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Modele economique :</div>
            <div class="col d-flex justify-content-start">${game.businessModel.name}</div>
        </div>
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Platformes :</div>
            <div class="col d-flex justify-content-start">
                <c:forEach items="${game.platforms}" var="platform">
                    <span class="me-2 px-1 border rounded">${platform.name}</span>
                </c:forEach>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-2 d-flex justify-content-end">Sortie :</div>
            <div class="col d-flex justify-content-start">${dateUtils.getDateFormat(game.publishedAt, "dd MMMM yyyy")}</div>
        </div>
    </div>
    <h2 class="mb-3 mt-5">Description</h2>
    <p class="mb-3">${game.description}</p>
    <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW_NEW}">Laisser un avis sur ce jeu</a>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
