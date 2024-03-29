<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="review_show"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="bg-dark bg-opacity-75 rounded p-3">
    <h1 class="mb-2">Avis du joueur ${review.gamer.nickname} sur le jeu ${review.game.name}</h1>
    <div class="col-12">
        <p class="mb-2">
            Commentaire ecris le ${dateUtils.getDateFormat(review.createdAt, "dd MMMM yyyy à hh:mm")}
        </p>
        <c:if test="${review.moderator == null}">
            Ce commentaire n'as pas encore etait modéré
            <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a class="btn btn-success" >
                    <i class="fa-regular fa-circle-check"></i>
                </a>
                <a class="btn btn-danger" >
                    <i class="fa-regular fa-trash-can"></i>
                </a>
            </security:authorize>
        </c:if>
        <div class="d-flex justify-content-start">
            <h2 class="mb-3 mt-5 me-5">Commentaire</h2>
            <div class="mt-5 mb-5"> Note : ${jspUtils.getStringRating(review.rating)}</div>
        </div>
        <p class="mb-3">${review.description}</p>
    </div>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
