<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1 class="mb-2">Avis du joueur ${review.gamer.nickname} sur le jeu ${review.game.name}</h1>
<div class="col-12">
    <p class="mb-2">
        Commentaire ecris le
        <fmt:formatDate value="${review.createdAt}" pattern="dd MMMMM yyyy - hh:mm"/>
    </p>
    <c:if test="${review.moderator == null}">
            Ce commentaire n'as pas encore etait modéré
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <a class="btn btn-success" >Valider</a>
            <a class="btn btn-danger" >Supprimer</a>
        </security:authorize>
    </c:if>
    <h2 class="mb-3 mt-5">Commentaire</h2>
    <p class="mb-3">${review.description}</p>
    <p>${review.rating}</p>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
