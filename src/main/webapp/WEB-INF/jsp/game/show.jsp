<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="game"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1 class="mb-2">${game.name}</h1>
<div class="col-12">
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a class="btn btn-success" href="${UrlRoute.URL_GAME_EDIT}">Modifier</a>
        <a class="btn btn-danger" >Supprimer</a>
    </security:authorize>
    <div class="row">
        <div class="col-4">
            Genre :
        </div>
    </div>
    <p>
        ${game.name} est un jeu de type ${game.genre.name}
    </p>
    <p>
        Sa classification est ${game.classification.name}
    </p>
    <p>
        Il est édité par ${game.publisher.name}
    </p>
    <p>
        Son business model est ${game.businessModel.name}
    </p>
    <p>
        Il est disponible sur
        <c:forEach items="${game.platforms}" var="platform">
            <span>${platform.name} </span>
        </c:forEach>
    </p>
    <h2 class="mb-3 mt-5">Description</h2>
    <p class="mb-3">${game.description}</p>
    <a href="${UrlRoute.URL_REVIEW_NEW}">Laisser un avis sur ce jeu</a>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
