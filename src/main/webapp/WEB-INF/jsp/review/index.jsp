<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="reviews"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les avis</h1>
<div class="col-12">
    <div class="d-flex">
        <c:set var="label" scope="request" value="Date"/>
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="../component/sortable.jsp" %>
<%--        <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/sortable.jsp"/>--%>

        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Jeu"/>
        <c:set var="sortable" value="game.name"/>
        <%@ include file="../component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Joueur"/>
        <c:set var="sortable" value="gamer.nickname"/>
        <%@ include file="../component/sortable.jsp" %>

        <span class="mt-auto mb-2">
            <a href="${currentUrl}" class="btn-link">
                Reset
            </a>
        </span>
    </div>
    <table class="table table-dark table-hover <c:if test="${!userLogged.veryEccentric}">table-striped-columns</c:if>">
        <thead>
            <tr>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Date de creation</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Jeu</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Joueur</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Note</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Statut</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageReviews.content}" var="review">
                    <tr>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${dateUtils.getDateFormat(review.createdAt, "dd MMMM yyyy à hh:mm")}</td>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${review.game.name}</td>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${review.gamer.nickname}</td>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${review.rating}</td>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>
                            <c:choose>
                                <c:when test="${review.moderator == null}">
                                    A moderer
                                </c:when>
                                <c:otherwise>
                                    Moderer par ${review.moderator.nickname}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>
                            <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW}/${review.id}">
                                <i class="fa-regular fa-eye"></i>
                            </a>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <c:if test="${review.moderator == null}">
                                    <a class="btn btn-success" href="${UrlRoute.URL_REVIEW_VALIDATE}/${review.id}">
                                        <i class="fa-regular fa-circle-check"></i>
                                    </a>
                                    <a class="btn btn-danger" href="${UrlRoute.URL_REVIEW_DELETE}/${review.id}">
                                        <i class="fa-regular fa-trash-can"></i>
                                    </a>
                                </c:if>
                            </security:authorize>
                        </td>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
    <a href="${UrlRoute.URL_REVIEW_NEW}">Nouveau commentaire</a>
    ${jspUtils.getPagination(pageReviews, currentUrl, currentQuery)}
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
